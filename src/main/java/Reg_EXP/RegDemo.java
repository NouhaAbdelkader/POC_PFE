package Reg_EXP;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Entrez votre regex : ");
            String regex = scanner.nextLine();
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

            System.out.print("Entrez la chaîne à rechercher : ");
            String input = scanner.nextLine();

            Matcher matcher = pattern.matcher(input);

            boolean found = false;
            while (matcher.find()) {
                System.out.printf("J'ai trouvé le texte \"%s\" commençant à l'index %d et se terminant à l'index %d.%n",
                        matcher.group(), matcher.start(), matcher.end());
                found = true;
            }
            if (!found) {
                System.out.println("Aucune correspondance trouvée.");
            }
        }



        // ************************ PatternSyntaxException **********************
        /**
         * try{
         *                 pattern =
         *                 Pattern.compile(console.readLine("%nEnter your regex: "));
         *
         *                 matcher =
         *                 pattern.matcher(console.readLine("Enter input string to search: "));
         *             }
         *             catch(PatternSyntaxException pse){
         *                 console.format("There is a problem" +
         *                                " with the regular expression!%n");
         *                 console.format("The pattern in question is: %s%n",
         *                                pse.getPattern());
         *                 console.format("The description is: %s%n",
         *                                pse.getDescription());
         *                 console.format("The message is: %s%n",
         *                                pse.getMessage());
         *                 console.format("The index is: %s%n",
         *                                pse.getIndex());
         *                 System.exit(0);
         *             }
         */
    }
}
