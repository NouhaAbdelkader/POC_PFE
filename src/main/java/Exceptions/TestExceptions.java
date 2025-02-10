package Exceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestExceptions {



    // ************************** Exception vérifiée *******************************
    // Exemple : Lire un fichier qui n'existe pas.
    // Cette méthode peut lever une exception de type FileNotFoundException,
    // donc il est obligatoire de gérer cette exception avec un try-catch ou de la déclarer avec 'throws'.

    public static void lireFichier(String nomFichier) throws FileNotFoundException {
        File fichier = new File(nomFichier); // Crée un objet File représentant le fichier à lire
        Scanner scanner = null;
        try {
            scanner = new Scanner(fichier); // Le scanner permet de lire le fichier. Il peut lever une FileNotFoundException si le fichier n'existe pas.
            // On lit le fichier ligne par ligne.
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine()); // Affiche chaque ligne du fichier
            }
        } finally {
            // Le bloc finally s'exécute toujours, même en cas d'exception. Ici, on ferme le scanner pour libérer les ressources.
            if (scanner != null) {
                scanner.close(); // Ferme le scanner pour éviter les fuites de mémoire
            }
        }
    }
    // ***************************** Exception d'exécution ********************************
    // Exemple : Accès à un élément hors limites d'un tableau.
    // Il n'est pas nécessaire d'ajouter 'throws' ici, mais on peut capturer l'exception avec un try-catch si nécessaire.

    public static void accederTableau() {
        int[] nombres = {1, 2, 3}; // Crée un tableau de 3 entiers
        System.out.println(nombres[5]); // Essaye d'accéder à un index qui n'existe pas dans le tableau
        // Cela génère une ArrayIndexOutOfBoundsException
    }

    // ******************************** Lever une Exception Personnalisée *************************
    // Cette méthode vérifie si l'âge est inférieur à 18 et lève une exception personnalisée si l'âge n'est pas valide.

    public static void checkAge(int age) throws AgeNotValidException {
        if (age < 18) {
            // Si l'âge est inférieur à 18, on lance une exception personnalisée avec un message d'erreur.
            throw new AgeNotValidException("Âge invalide ! Vous devez avoir au moins 18 ans.");
        } else {
            // Si l'âge est valide, on affiche un message autorisant l'accès
            System.out.println("Accès autorisé 🎉 !");
        }
    }

    public static void main(String[] args) {
        // Test 1 : Lecture d'un fichier inexistant
        try {
            lireFichier("fichier_inexistant.txt"); // Essaie de lire un fichier qui n'existe pas
        } catch (FileNotFoundException e) {
            // Si le fichier n'existe pas, on capture l'exception et on affiche un message d'erreur
            System.out.println("Erreur : Fichier introuvable !");
        }

        // Test 2 : Accès hors limites d'un tableau
        try {
            accederTableau(); // Essaie d'accéder à un index hors limites dans un tableau
        } catch (ArrayIndexOutOfBoundsException e) {
            // Si l'index est invalide, on capture l'exception et on affiche un message d'erreur
            System.out.println("Erreur : Tentative d'accès hors limites du tableau !");
        }

        // Test 3 : Vérification de l'âge avec une exception personnalisée
        try {
            checkAge(16); // Essaie de vérifier un âge inférieur à 18, ce qui déclenchera l'exception personnalisée
        } catch (AgeNotValidException e) {
            // Si l'exception personnalisée est lancée, on capture l'exception et on affiche le message d'erreur
            System.out.println("Exception capturée : " + e.getMessage());
        }
    }
}
