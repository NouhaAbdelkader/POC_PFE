package Files;

import java.io.*;
import java.nio.file.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class IOStreamsDecorator {

    // Le pattern Décorateur est utilisé pour "décorer" des classes de lecture et d'écriture en ajoutant des fonctionnalités sans modifier leur structure de base.
    public void readSonnet() {
        Path path = Path.of("files/sonnet.txt");
        String sonnet = null;
        try (var inputStream = Files.newInputStream(path);
             var reader = new InputStreamReader(inputStream);
             var bufferedReader = new BufferedReader(reader);
             var stream = bufferedReader.lines()) {

            sonnet = stream.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("sonnet = \n" + sonnet);
    }

    public void writeSonnet() throws IOException {
        String message = "..."; // Sonnet de Shakespeare
        Path path = Path.of("files/sonnet.txt");
        try (var outputStream = Files.newOutputStream(path);
             var writer = new OutputStreamWriter(outputStream)) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long size = Files.size(path);
        System.out.println("size = " + size); // Affiche la taille du fichier
    }

    public void writeCompressedSonnet() throws IOException {
        String message = "..."; // Sonnet de Shakespeare
        Path path = Path.of("files/sonnet.txt.gz");
        try (var outputStream = Files.newOutputStream(path);
             var gzipOutputStream = new GZIPOutputStream(outputStream);
             var writer = new OutputStreamWriter(gzipOutputStream)) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long size = Files.size(path);
        System.out.println("size = " + size); // Affiche la taille du fichier compressé
    }

    public void readCompressedSonnet() {
        Path path = Path.of("files/sonnet.txt.gz");
        String sonnet = null;
        try (var inputStream = Files.newInputStream(path);
             var gzipInputStream = new GZIPInputStream(inputStream);
             var reader = new InputStreamReader(gzipInputStream);
             var bufferedReader = new BufferedReader(reader);
             var stream = bufferedReader.lines()) {

            sonnet = stream.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("sonnet = \n" + sonnet);
    }

    public void writePrimitives() throws IOException {
        int[] ints = {3, 1, 4, 1, 5, 9};
        Path path = Path.of("files/ints.bin");
        try (var outputStream = Files.newOutputStream(path);
             var dataOutputStream = new DataOutputStream(outputStream)) {
            for (int i : ints) {
                dataOutputStream.writeInt(i); // Écriture de chaque int dans le fichier binaire
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long size = Files.size(path);
        System.out.println("size = " + size);
    }

    public void readPrimitives() {
        Path path = Path.of("files/ints.bin");
        int[] ints = new int[6];
        try (var inputStream = Files.newInputStream(path);
             var dataInputStream = new DataInputStream(inputStream)) {
            for (int index = 0; index < ints.length; index++) {
                ints[index] = dataInputStream.readInt(); // Lecture de chaque int depuis le fichier binaire
            }
            System.out.println("ints = " + Arrays.toString(ints));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode main pour exécuter toutes les fonctionnalités
    public static void main(String[] args) {
        IOStreamsDecorator io = new IOStreamsDecorator();

        // Créer les répertoires nécessaires si inexistants
        Path dirPath = Path.of("files");
        if (Files.notExists(dirPath)) {
            try {
                Files.createDirectories(dirPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            io.writeSonnet();
            io.readSonnet();
            io.writeCompressedSonnet();
            io.readCompressedSonnet();
            io.writePrimitives();
            io.readPrimitives();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
