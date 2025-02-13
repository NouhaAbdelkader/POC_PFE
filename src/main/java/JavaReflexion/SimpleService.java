package JavaReflexion;

public class SimpleService {

    @Intercept
    public void sayHello() {
        System.out.println("Hello, World!");
    }
}
