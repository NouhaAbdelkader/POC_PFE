package com.example.poc_pfe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@SpringBootApplication
public class PocPfeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PocPfeApplication.class, args);

        // Exécuter les différentes démonstrations
        unboxingExample();
        switchExample();
        operationsExample();
        arrayCopyExample();
    }

    // **************************** Unboxing ****************************
    private static void unboxingExample() {
        Integer i = Integer.valueOf(-8);

        // 1. Unboxing through method invocation
        int absVal = absoluteValue(i);
        System.out.println("absolute value of " + i + " = " + absVal);

        List<Double> doubles = new ArrayList<>();
        doubles.add(3.1416); // Π is autoboxed through method invocation.

        // 2. Unboxing through assignment
        double pi = doubles.get(0);
        System.out.println("pi = " + pi);
    }

    public static int absoluteValue(int i) {
        return (i < 0) ? -i : i;
    }

    // ************************* Switch *************************
    private static void switchExample() {
        int month = 8;
        List<String> futureMonths = new ArrayList<>();
        switch (month) {
            case 1 -> futureMonths.add("January");
            case 2 -> futureMonths.add("February");
            case 3 -> futureMonths.add("March");
            case 4 -> futureMonths.add("April");
            case 5 -> futureMonths.add("May");
            case 6 -> futureMonths.add("June");
            case 7 -> futureMonths.add("July");
            case 8 -> futureMonths.add("August");
            case 9 -> futureMonths.add("September");
            case 10 -> futureMonths.add("October");
            case 11 -> futureMonths.add("November");
            case 12 -> futureMonths.add("December");
            default -> System.out.println("Invalid month");
        }

        System.out.println("Future months: " + futureMonths);

        // Switch avec expressions (Java 14+)
        String day = "MONDAY";

        int len = switch (day) {
            case "MONDAY", "FRIDAY", "SUNDAY" -> 6;
            case "TUESDAY" -> 7;
            case "THURSDAY", "SATURDAY" -> 8;
            case "WEDNESDAY" -> 9;
            default -> 0;
        };

        System.out.println("len = " + len);
    }

    // ************************ Operations ***************************
    private static void operationsExample() {
        int result = +1;
        System.out.println(result); // 1

        result--;
        System.out.println(result); // 0

        result++;
        System.out.println(result); // 1

        result = -result;
        System.out.println(result); // -1

        boolean success = false;
        System.out.println(success);  // false
        System.out.println(!success); // true
    }

    // ************************ Arrays ***************************
    private static void arrayCopyExample() {
        String[] copyFrom = {
                "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",
                "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo", "Macchiato",
                "Marocchino", "Ristretto"
        };

        String[] copyTo = new String[7];

        // Copier le tableau
        System.arraycopy(copyFrom, 2, copyTo, 0, 7);

        // Afficher les valeurs copiées
        System.out.println("Array copied: " + String.join(", ", copyTo));

        // Copier sans créer un second tableau
        String[] copyTo2 = Arrays.copyOfRange(copyFrom, 2, Math.min(copyFrom.length, 9));
        System.out.println("Copied using copyOfRange: " + String.join(", ", copyTo2));
    }
}
