package Conversion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.*;

public class LoopConversionExample {

    public static void main(String[] args) {

        // Exemple 1 : Conversion d'une boucle simple (for) -> style fonctionnel avec range()
        System.out.println("---- Conversion d'une boucle simple (for) -> style fonctionnel ----");
        // Style impératif :
        System.out.println("Style impératif : ");
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }

        // Style fonctionnel :
        System.out.println("Style fonctionnel : ");
        // Utilisation de range() pour itérer à travers les valeurs
        IntStream.range(0, 5).forEach(System.out::println);
        // ou bien si c'est <= 5 alors :
        IntStream.rangeClosed(0, 5).forEach(System.out::println);

        // Exemple 2 : Conversion de boucles avec des pas (i = i + ...) -> style fonctionnel avec iterate()
        System.out.println("\n---- Conversion de boucles avec des pas -> style fonctionnel ----");
        // Style impératif :
        System.out.println("Style impératif : ");
        for (int i = 0; i < 10; i += 2) {
            System.out.println(i);
        }

        // Style fonctionnel :
        System.out.println("Style fonctionnel : ");
        // Utilisation de iterate() avec takeWhile() pour boucler avec des pas
        Stream.iterate(0, i -> i + 2).takeWhile(i -> i < 10).forEach(System.out::println);

        // Exemple 3 : Conversion de foreach avec if -> style fonctionnel avec filter()
        System.out.println("\n---- Conversion de foreach avec if -> style fonctionnel ----");
        // Style impératif :
        System.out.println("Style impératif : ");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        for (int number : numbers) {
            if (number % 2 == 0) {
                System.out.println(number);
            }
        }

        // Style fonctionnel :
        System.out.println("Style fonctionnel : ");
        // Utilisation de filter() pour obtenir les nombres pairs
        numbers.stream().filter(number -> number % 2 == 0).forEach(System.out::println);

        // Exemple 4 : Conversion d'une itération avec transformation (foreach avec transformation) -> style fonctionnel avec map()
        System.out.println("\n---- Conversion d'une itération avec transformation -> style fonctionnel ----");
        // Style impératif :
        System.out.println("Style impératif : ");
        List<String> words = Arrays.asList("hello", "world", "java");
        for (String word : words) {
            System.out.println(word.toUpperCase());
        }

        // Style fonctionnel :
        System.out.println("Style fonctionnel : ");
        // Utilisation de map() pour transformer chaque mot en majuscule
        words.stream().map(String::toUpperCase).forEach(System.out::println);

        // Exemple 5 : Conversion de l'opération de lecture de fichier -> style fonctionnel avec Files.lines()
        System.out.println("\n---- Conversion de l'opération de lecture de fichier -> style fonctionnel ----");
        // Style impératif :
        System.out.println("Style impératif : ");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("sample.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Style fonctionnel :
        System.out.println("Style fonctionnel : ");
        // Utilisation de Files.lines() pour lire les lignes d'un fichier sous forme de stream
        try {
            Files.lines(Paths.get("sample.txt")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Exemple supplémentaire : Conversion de foreach avec if (Filtrer des éléments) -> style fonctionnel avec filter()
        System.out.println("\n---- Conversion de foreach avec if (Filtrer des éléments) -> style fonctionnel ----");
        // Style impératif :
        System.out.println("Style impératif : ");
        List<String> names = List.of("Jack", "Paula", "Kate", "Peter");
        for (String name : names) {
            if (name.length() == 4) {
                System.out.println(name);
            }
        }

        // Style fonctionnel :
        System.out.println("Style fonctionnel : ");
        // Utilisation de filter() pour sélectionner des éléments selon la condition
        names.stream()
                .filter(name -> name.length() == 4)  // Filtrage des noms de longueur 4
                .forEach(System.out::println);       // Affichage des noms filtrés

        // Exemple supplémentaire : Conversion de la somme des éléments -> style fonctionnel avec reduce()
        System.out.println("\n---- Conversion de la somme des éléments -> style fonctionnel ----");
        // Style impératif :
        System.out.println("Style impératif : ");
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        System.out.println("Somme (impératif) : " + sum);

        // Style fonctionnel :
        System.out.println("Style fonctionnel : ");
        // Utilisation de reduce() pour calculer la somme des éléments de la liste
        int functionalSum = numbers.stream().reduce(0, Integer::sum);
        System.out.println("Somme (fonctionnel) : " + functionalSum);
    }
}
