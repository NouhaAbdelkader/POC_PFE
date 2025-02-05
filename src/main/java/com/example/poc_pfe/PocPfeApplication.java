package com.example.poc_pfe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PocPfeApplication {
//first commit

    public static void main(String[] args) {
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
