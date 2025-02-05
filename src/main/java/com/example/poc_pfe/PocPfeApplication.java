package com.example.poc_pfe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PocPfeApplication {
//first commit

    public static void main(String[] args) {

//*************************Switch *********************************

        int month = 8;
        List<String> futureMonths = new ArrayList<>();
        switch (month) {
        case 1:  futureMonths.add("January");
          case 2:  futureMonths.add("February");
         case 3:  futureMonths.add("March");
         case 4:  futureMonths.add("April");
        case 5:  futureMonths.add("May");
        case 6:  futureMonths.add("June");
         case 7:  futureMonths.add("July");
        case 8:  futureMonths.add("August");
        case 9:  futureMonths.add("September");
        case 10: futureMonths.add("October");
        case 11: futureMonths.add("November");
        case 12: futureMonths.add("December");
          break;
        default: break;}


        String day = "MONDAY";
        // Exemple de valeur

        int len = switch (day) {
            case "MONDAY", "FRIDAY", "SUNDAY" -> 6;
            case "TUESDAY"                    -> 7;
            case "THURSDAY", "SATURDAY"       -> 8;
            case "WEDNESDAY"                  -> 9;
            default                           -> 0; // Valeur par défaut si `day` ne correspond à rien
        };

        System.out.println("len = " + len);




        // ************************ Operations *************************** //

        int result = +1;
        // result is now 1
        System.out.println(result);

        result--;
        // result is now 0
        System.out.println(result);

        result++;
        // result is now 1
        System.out.println(result);

        result = -result;
        // result is now -1
        System.out.println(result);

        boolean success = false;
        // false
        System.out.println(success);
        // true
        System.out.println(!success);


        SpringApplication.run(PocPfeApplication.class, args);

        // ************************ arrays *************************** //

        // copying arrays
        String[] copyFrom = {
                "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",
                "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo", "Macchiato",
                "Marocchino", "Ristretto" };

        String[] copyTo = new String[7];
        ///////
        System.arraycopy(copyFrom, 2, copyTo, 0, 7);
        //////

        for (String coffee : copyTo) {
            System.out.print(coffee + " ");
        }    }



    // copying arrays without creationg second array

    String[] copyFrom = { "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado", "Doppio", "Espresso" };
    String[] copyTo = java.util.Arrays.copyOfRange(copyFrom, 2, 9);








}
