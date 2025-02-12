package Files;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class FileOperations {

    public FileOperations() throws IOException {
    }

    // Méthode pour lire un fichier texte avec BufferedReader
    public static void readTextFile(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))) {
            String line;
            long count = 0;
            while ((line = reader.readLine()) != null) {
                count++;
            }
            System.out.println("Number of lines in the file: " + count);
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    // Méthode pour lire un fichier texte en tant que Stream
    public static void readTextFileWithStream(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
             Stream<String> lines = reader.lines()) {
            long count = lines.count();
            System.out.println("Total lines count using stream: " + count);
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    // Méthode pour écrire un fichier texte avec BufferedWriter
    public static void writeTextFile(Path file, String content) {
        try (BufferedWriter writer = Files.newBufferedWriter(file, Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            writer.write(content);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    // Méthode pour créer un fichier avec des attributs personnalisés
    public static void createFileWithAttributes(Path path) {
        try {
            Files.createFile(path, new FileAttribute<?>[0]);
            System.out.println("File created successfully.");
        } catch (FileAlreadyExistsException e) {
            System.err.format("File %s already exists.%n", path);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    // Exemple de lecture des octets d'un fichier
    public static void readAllBytesFromFile(Path file) {
        try {
            byte[] fileArray = Files.readAllBytes(file);
            System.out.println("File content in bytes: " + new String(fileArray));
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    // Exemple de création d'un fichier temporaire
    public static void createTempFile() {
        try {
            Path tempFile = Files.createTempFile("tempfile", ".txt");
            System.out.println("Temporary file created: " + tempFile.toString());
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    // Exemple de lecture et écriture via FileChannel (Random Access)
    public static void readWriteWithFileChannel(Path file) {
        try (FileChannel fileChannel = FileChannel.open(file, StandardOpenOption.READ, StandardOpenOption.WRITE)) {
            ByteBuffer buffer = ByteBuffer.allocate(48);
            fileChannel.read(buffer);
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();
            String textToWrite = "New data at position " + fileChannel.position();
            buffer.put(textToWrite.getBytes());
            buffer.flip();
            fileChannel.write(buffer);
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Path file = Path.of("example.txt");

        // Lecture du fichier texte
        readTextFile(file);

        // Lecture du fichier avec Stream
        readTextFileWithStream(file);

        // Écriture dans le fichier texte
        writeTextFile(file, "This is a new content for the file.");

        // Création d'un fichier avec des attributs
        createFileWithAttributes(Path.of("newFile.txt"));

        // Lecture de tous les octets du fichier
        readAllBytesFromFile(file);

        // Création d'un fichier temporaire
        createTempFile();

        // Lecture et écriture via FileChannel (Random Access)
        readWriteWithFileChannel(file);


// Exemple d'écriture dans un fichier binaire avec OutputStream
        String message = "Logging to binary file!";
        byte[] data = message.getBytes();
        Path logFile = Path.of("logfile.dat");
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(logFile, StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException e) {
            System.err.println("IOException: " + e);
        }

        // Exemple d'utilisation de canaux pour lire un fichier binaire avec SeekableByteChannel
        Path filePath = Path.of("binaryFile.dat");
        try (
                SeekableByteChannel sbc = Files.newByteChannel(filePath)) {
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (sbc.read(buffer) > 0) {
                buffer.flip();
                System.out.print(Charset.defaultCharset().decode(buffer));
                buffer.clear();
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e);
        }

    }


}
