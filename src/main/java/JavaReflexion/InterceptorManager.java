package JavaReflexion;
import java.lang.reflect.Method;

public class InterceptorManager {

    public static void invoke(Object service, String methodName) {
        try {
            // Récupérer la méthode
            Method method = service.getClass().getMethod(methodName);

            // Vérifier si la méthode a l'annotation Intercept
            if (method.isAnnotationPresent(Intercept.class)) {
                // Interception de la méthode avant et après l'exécution
                SimpleInterceptor.intercept(service, methodName);
            }

            // Appel de la méthode réelle après l'interception
            method.invoke(service);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
