package Files;

import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.nio.file.Path;
import java.util.*;
import java.util.zip.GZIPOutputStream;
import java.nio.file.*;

class Sonnet {

    /**
     * Résumé du processus global :
     * Téléchargement des sonnets depuis Project Gutenberg via HTTP.
     * Lecture et extraction des sonnets ligne par ligne.
     * Compression de chaque sonnet en GZIP.
     * Stockage binaire de ces sonnets compressés dans un fichier local sonnets.bin, avec des informations sur leur emplacement dans le fichier pour pouvoir les lire plus tard.
     */
    private List<String> lines = new ArrayList<>();

    public void add(String line) {
        lines.add(line);
    }

    byte[] getCompressedBytes() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (GZIPOutputStream gzos = new GZIPOutputStream(bos);
             PrintWriter printWriter = new PrintWriter(gzos)) {
            for (String line : lines) {
                printWriter.println(line);
            }
        }
        return bos.toByteArray();
    }
}

class SonnetReader extends BufferedReader {

    public SonnetReader(Reader reader) {
        super(reader);
    }

    public SonnetReader(InputStream inputStream) {
        this(new InputStreamReader(inputStream));
    }

    public void skipLines(int lines) throws IOException {
        for (int i = 0; i < lines; i++) {
            readLine();
        }
    }

    private String skipSonnetHeader() throws IOException {
        String line = readLine();
        while (line.isBlank()) {
            line = readLine();
        }
        if (line.startsWith("*** END OF THE PROJECT GUTENBERG EBOOK")) {
            return null;
        }
        line = readLine();
        while (line.isBlank()) {
            line = readLine();
        }
        return line;
    }

    public Sonnet readNextSonnet() throws IOException {
        String line = skipSonnetHeader();
        if (line == null) {
            return null;
        } else {
            Sonnet sonnet = new Sonnet();
            while (!line.isBlank()) {
                sonnet.add(line);
                line = readLine();
            }
            return sonnet;
        }
    }



    public static void main(String[] args) {
        URI sonnetsURI = URI.create("https://www.gutenberg.org/cache/epub/1041/pg1041.txt");  // Remplacer par le lien de votre choix
        HttpRequest request = HttpRequest.newBuilder(sonnetsURI).GET().build();
        HttpClient client = HttpClient.newBuilder().build();

        try {
            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            InputStream inputStream = response.body();

            int start = 33;  // Skip initial lines
            List<Sonnet> sonnets = new ArrayList<>();

            try (var reader = new SonnetReader(inputStream)) {
                reader.skipLines(start);
                Sonnet sonnet = reader.readNextSonnet();
                while (sonnet != null) {
                    sonnets.add(sonnet);
                    System.out.println("Sonnet " + sonnets.size() + " ajouté.");
                    sonnet = reader.readNextSonnet();
                }
            }

            System.out.println("# sonnets = " + sonnets.size());

            // Write all sonnets to a compressed binary file
            int numberOfSonnets = sonnets.size();
            Path sonnetsFile = Path.of("files/sonnets.bin");

            try (var sonnetFile = Files.newOutputStream(sonnetsFile);
                 var dos = new DataOutputStream(sonnetFile)) {

                List<Integer> offsets = new ArrayList<>();
                List<Integer> lengths = new ArrayList<>();
                byte[] encodedSonnetsBytesArray = null;

                try (ByteArrayOutputStream encodedSonnets = new ByteArrayOutputStream()) {
                    for (int i = 0; i < numberOfSonnets; i++) {
                        Sonnet s = sonnets.get(i);
                        byte[] sonnetCompressedBytes = s.getCompressedBytes();
                        System.out.println("Compression du sonnet " + (i + 1) + " en cours...");
                        offsets.add(encodedSonnets.size());
                        lengths.add(sonnetCompressedBytes.length);
                        encodedSonnets.write(sonnetCompressedBytes);
                    }

                    dos.writeInt(numberOfSonnets);
                    for (int index = 0; index < numberOfSonnets; index++) {
                        dos.writeInt(offsets.get(index));
                        dos.writeInt(lengths.get(index));
                    }

                    encodedSonnetsBytesArray = encodedSonnets.toByteArray();
                }
                sonnetFile.write(encodedSonnetsBytesArray);
                System.out.println("Tous les sonnets ont été compressés et sauvegardés dans : " + sonnetsFile.toAbsolutePath());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
