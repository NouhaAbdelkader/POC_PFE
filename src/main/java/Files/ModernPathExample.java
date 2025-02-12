package Files;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


public class ModernPathExample {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("example.txt");
        /////////////********* Attributs de fichier de base *************/////////////
        System.out.println("/////////////********* Attributs de fichier de base *************/////////////\n" );

        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);

        System.out.println("creationTime: " + attr.creationTime());
        System.out.println("lastAccessTime: " + attr.lastAccessTime());
        System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

        System.out.println("isDirectory: " + attr.isDirectory());
        System.out.println("isOther: " + attr.isOther());
        System.out.println("isRegularFile: " + attr.isRegularFile());
        System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
        System.out.println("size: " + attr.size());

        // Vérifier si le fichier existe
        System.out.println("// Vérifier si le fichier existe\n" );

        if (Files.exists(path)) {
        } else {
            try {
                Files.createFile(path);
                System.out.println("Fichier créé : " + path.toAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Afficher les informations du fichier
        System.out.println(" Afficher les informations du fichier\n" );

        System.out.println("Nom du fichier : " + path.getFileName());
        System.out.println("Chemin absolu : " + path.toAbsolutePath());
        System.out.println("Est un fichier : " + Files.isRegularFile(path));
        System.out.println("Est un répertoire : " + Files.isDirectory(path));

        /////////////////////   Création de chemins   //////////////
        System.out.println(" // Création de chemins\n" );

        Path p1 = Paths.get("/tmp/foo");
        Path p2 = Paths.get(System.getProperty("user.home"), "logs", "foo.log");
        Path p3 = Paths.get(URI.create("file:///Users/joe/FileTest.java"));
        Path p4 = Path.of("/tmp/debug.log");

        // Récupération d'informations sur un chemin
        System.out.println(" Récupération d'informations sur un chemin\n" );

        Path path1 = Paths.get("/home/joe/foo");
        System.out.println("Nom du fichier : " + path1.getFileName());
        System.out.println("Nom du premier répertoire : " + path1.getName(0));
        System.out.println("Nombre d'éléments dans le chemin : " + path1.getNameCount());
        System.out.println("Sous-chemin (0,2) : " + path1.subpath(0, 2));
        System.out.println("Répertoire parent : " + path1.getParent());
        System.out.println("Racine : " + path1.getRoot());

        // Normalisation d'un chemin
        Path redundantPath = Paths.get("/home/./joe/foo");
        System.out.println("Chemin normalisé : " + redundantPath.normalize());

        // Conversion de chemin
        System.out.println("URI : " + path.toUri());
        System.out.println("Chemin absolu : " + path.toAbsolutePath());
        try {
            System.out.println("Chemin réel : " + path.toRealPath());
        } catch (IOException e) {
            System.err.println("Erreur d'accès au chemin réel : " + e.getMessage());
        }

        // Joindre deux chemins
        Path basePath = Paths.get("/home/joe");
        Path resolvedPath = basePath.resolve("documents/report.pdf");
        System.out.println("Chemin résolu : " + resolvedPath);


        ///////////////// **************  FileSystemAccess   *************  //////////////////////


        // Récupération du système de fichiers par défaut
        FileSystem fileSystem = FileSystems.getDefault();

        // Création d'un PathMatcher avec un motif glob
        PathMatcher matcher = fileSystem.getPathMatcher("glob:*.*");
        System.out.println("PathMatcher créé avec un motif glob.");


        // Récupération du séparateur de chemin
        String separator1 = File.separator;
        String separator2 = fileSystem.getSeparator();
        System.out.println("Séparateur de chemin (File): " + separator1);
        System.out.println("Séparateur de chemin (FileSystem): " + separator2);

        // Liste des magasins de fichiers disponibles
        System.out.println("Liste des magasins de fichiers disponibles:");
        for (FileStore store : fileSystem.getFileStores()) {
                System.out.println(store.name() + " - " + store.type());
        }

        // Vérification de l'accessibilité des répertoires racines
        System.out.println("Accessibilité des répertoires racines:");
        for (Path rootDirectory : fileSystem.getRootDirectories()) {
            boolean readable = Files.isReadable(rootDirectory);
            System.out.println("Répertoire: " + rootDirectory + " - Lisible: " + readable);
        }

        ////// ***********  FileManagerExample ***********   //////



        Path source = Paths.get("source.txt");
        Path destination = Paths.get("backup/source_backup.txt");

        try {
            // Vérifier si le fichier existe
            if (Files.exists(source)) {
                System.out.println(source + " existe.");

                // Vérifier les permissions
                if (Files.isReadable(source) && Files.isWritable(source)) {
                    System.out.println("Le fichier est accessible en lecture et écriture.");

                    // Copier le fichier
                    Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Fichier copié avec succès vers " + destination);
                } else {
                    System.out.println("Le fichier n'est pas accessible en lecture ou écriture.");
                }

                // Vérifier si deux chemins pointent vers le même fichier
                if (Files.isSameFile(source, destination)) {
                    System.out.println("Les deux chemins pointent vers le même fichier.");
                } else {
                    System.out.println("Les fichiers sont différents.");
                }

                // Supprimer le fichier de destination après la copie
                Files.deleteIfExists(destination);
                System.out.println("Fichier de sauvegarde supprimé.");
            } else {
                System.out.println(source + " n'existe pas.");
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la manipulation du fichier : " + e.getMessage());
        }

          //// **********  Liens, symboliques et autres ****************



        System.err.println("**********  Liens, symboliques et autres ****************");

        try {
            // Définition des chemins
            Path targetFile = Paths.get("targetFile.txt"); // Fichier cible
            Path symbolicLink = Paths.get("symbolicLink.txt"); // Lien symbolique
            Path hardLink = Paths.get("hardLink.txt"); // Lien physique

            // Création du fichier cible s'il n'existe pas
            if (!Files.exists(targetFile)) {
                Files.createFile(targetFile);
                System.out.println("Fichier cible créé : " + targetFile);
            }

            // Création d'un lien symbolique
            createSymbolicLink(symbolicLink, targetFile);

            // Création d'un lien physique (hard link)
            createHardLink(hardLink, targetFile);

            // Vérification si un fichier est un lien symbolique
            checkIfSymbolicLink(symbolicLink);

            // Lecture de la cible d'un lien symbolique
            readSymbolicLinkTarget(symbolicLink);

        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    /**
     * Crée un lien symbolique vers un fichier cible.
     */
    private static void createSymbolicLink(Path link, Path target) {
        try {
            if (!Files.exists(link)) {
                Files.createSymbolicLink(link, target);
                System.out.println("Lien symbolique créé : " + link + " → " + target);
            } else {
                System.out.println("Le lien symbolique existe déjà : " + link);
            }
        } catch (UnsupportedOperationException e) {
            System.err.println("Les liens symboliques ne sont pas pris en charge sur ce système.");
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du lien symbolique : " + e.getMessage());
        }
    }

    /**
     * Crée un lien physique (hard link) vers un fichier existant.
     */
    private static void createHardLink(Path link, Path target) {
        try {
            if (!Files.exists(link)) {
                Files.createLink(link, target);
                System.out.println("Lien physique créé : " + link + " → " + target);
            } else {
                System.out.println("Le lien physique existe déjà : " + link);
            }
        } catch (UnsupportedOperationException e) {
            System.err.println("Les liens physiques ne sont pas pris en charge sur ce système.");
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du lien physique : " + e.getMessage());
        }
    }

    /**
     * Vérifie si un fichier est un lien symbolique.
     */
    private static void checkIfSymbolicLink(Path file) {
        if (Files.isSymbolicLink(file)) {
            System.out.println(file + " est un lien symbolique.");
        } else {
            System.out.println(file + " n'est PAS un lien symbolique.");
        }
    }

    /**
     * Lit la cible d'un lien symbolique.
     */
    private static void readSymbolicLinkTarget(Path link) {
        try {
            Path target = Files.readSymbolicLink(link);
            System.out.println("La cible du lien symbolique " + link + " est : " + target);
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du lien symbolique : " + e.getMessage());
        }
    }
}