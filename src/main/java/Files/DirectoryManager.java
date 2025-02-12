package Files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class DirectoryManager {
    public static void main(String[] args) {
        // 📌 1. Lister les répertoires racines du système
        System.out.println("📂 Répertoires racines du système :");
        Iterable<Path> rootDirs = FileSystems.getDefault().getRootDirectories();
        for (Path root : rootDirs) {
            System.out.println(root);
        }

        try {
            // 📌 2. Créer un répertoire simple
            Path dirPath = Paths.get("mon_repertoire");
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
                System.out.println("✅ Répertoire créé : " + dirPath);
            } else {
                System.out.println("ℹ️ Le répertoire existe déjà : " + dirPath);
            }

            // 📌 3. Créer un répertoire imbriqué (plusieurs niveaux)
            Path nestedDirPath = Paths.get("parent/enfant/petit_enfant");
            Files.createDirectories(nestedDirPath);
            System.out.println("✅ Répertoire imbriqué créé : " + nestedDirPath);

            // 📌 4. Créer un répertoire temporaire
            Path tempDir = Files.createTempDirectory("mon_temp_");
            System.out.println("✅ Répertoire temporaire créé : " + tempDir);

            // 📌 5. Lister le contenu d'un répertoire
            System.out.println("\n📂 Contenu du répertoire :");
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
                for (Path file : stream) {
                    System.out.println(file.getFileName());
                }
            } catch (IOException | DirectoryIteratorException x) {
                System.err.println(x);
            }

            // 📌 6. Filtrage des fichiers avec Glob
            System.out.println("\n📂 Contenu filtré (fichiers .java, .class, .jar) :");
            try (DirectoryStream<Path> stream =
                         Files.newDirectoryStream(dirPath, "*.{java,class,jar}")) {
                for (Path entry : stream) {
                    System.out.println(entry.getFileName());
                }
            } catch (IOException x) {
                System.err.println(x);
            }

            // 📌 7. Utiliser un filtre personnalisé pour lister seulement les répertoires
            DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
                public boolean accept(Path file) throws IOException {
                    return Files.isDirectory(file);  // Filtrer uniquement les répertoires
                }
            };

            System.out.println("\n📂 Liste des sous-répertoires :");
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath, filter)) {
                for (Path entry : stream) {
                    System.out.println(entry.getFileName());
                }
            } catch (IOException x) {
                System.err.println(x);
            }

        } catch (IOException e) {
            System.err.println("❌ Erreur : " + e.getMessage());
        }
    }
}
