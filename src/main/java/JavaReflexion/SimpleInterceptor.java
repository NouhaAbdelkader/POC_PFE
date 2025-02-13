package JavaReflexion;

public class SimpleInterceptor {
    public static void intercept(Object service, String methodName) {
        System.out.println("Avant l'exécution de la méthode : " + methodName);

        // Appel de la méthode réelle (on va le faire plus tard)
        System.out.println("Exécution de la méthode " + methodName);

        System.out.println("Après l'exécution de la méthode : " + methodName);
    }
}
