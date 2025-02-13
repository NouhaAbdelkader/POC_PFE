package JavaReflexion;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Java_Reflexion {

    /**
     * Java Reflection, une fonctionnalité puissante du langage Java qui permet d'inspecter
     *  et de manipuler dynamiquement les classes, méthodes et champs d'un programme à l'exécution.
     **/
    public static void main(String[] args) throws ReflectiveOperationException {

/**
 * intro
 */
        System.out.println("*********** introduction **********\n");
        var pi = "3141592653589793".chars()
                .map(i -> i - '0')
                .boxed().collect(Collectors.toList());
        System.out.println(pi);
        for (var method : Collections.class.getMethods()) {
            if (method.getReturnType() == void.class
                    && method.getParameterCount() == 1
                    && method.getParameterTypes()[0] == List.class) {
                System.out.println("Calling " + method.getName() + "()");
                method.invoke(null, pi);
                System.out.println(pi +"\n");
            }
        }

        /**
         * retreiving classes
         */

        System.out.println("********* retreiving classes  ******* \n");

        // 1. Obtenir la classe d'un objet
        Class<?> stringClass = "foo".getClass();
        System.out.println("Class of 'foo': " + stringClass);

        // 2. Obtenir la classe d'un Enum
        enum Days {SATURDAY, SUNDAY}
        Class<?> enumClass = Days.SATURDAY.getClass();
        System.out.println("Enum class: " + enumClass);

        // 3. Obtenir la classe d'un tableau
        byte[] byteArray = new byte[1024];
        Class<?> byteArrayClass = byteArray.getClass();
        System.out.println("Array class: " + byteArrayClass);

        // 4. Obtenir la classe d'un objet au runtime
        Set<String> set = new HashSet<>();
        Class<?> setClass = set.getClass();
        System.out.println("Runtime class of HashSet: " + setClass);

        // 5. Obtenir la classe d'une expression lambda
        Supplier<String> supplier = () -> "Hello";
        Class<?> lambdaClass = supplier.getClass();
        System.out.println("Lambda class: " + lambdaClass);

        // 6. Obtenir la classe d'une classe anonyme
        Object anonymous = new Object() {};
        Class<?> anonymousClass = anonymous.getClass();
        System.out.println("Anonymous class: " + anonymousClass);

        // 7. Utilisation de la syntaxe .class
        Class<?> printStreamClass = java.io.PrintStream.class;
        System.out.println("Class using .class: " + printStreamClass);

        // 8. Utilisation de Class.forName()
        Class<?> forNameClass = Class.forName("java.lang.String");
        System.out.println("Class using forName(): " + forNameClass);

        // 9. Utilisation de Class.forPrimitiveName() (Java 22+)
        // Class<?> primitiveClass = Class.forPrimitiveName("int");
        // System.out.println("Primitive class: " + primitiveClass);

        // 10. Obtenir la classe d'un type primitif depuis un wrapper
        Class<?> primitiveType = Double.TYPE;
        System.out.println("Primitive type from wrapper: " + primitiveType);

        // 11. Obtenir la super classe d'une classe
        Class<?> superClass = NullPointerException.class;
        while (superClass != null) {
            System.out.println("Superclass: " + superClass);
            superClass = superClass.getSuperclass();
        }

        // 12. Obtenir les interfaces implémentées par une classe
        Class<?>[] interfaces = String.class.getInterfaces();
        for (Class<?> iface : interfaces) {
            System.out.println("Implemented interface: " + iface +"\n");
        }


        /**
         * Reading Class Names
         **/

        System.out.println("******** Reading Class Names ******** \n");

        // Getting the canonical and simple names of a class
        Class<?> arrayList = Class.forName("java.util.ArrayList");
        System.out.println("Canonical name: " + arrayList.getCanonicalName());
        System.out.println("Simple name: " + arrayList.getSimpleName());

        // Getting names from runtime class instances
        List<String> strings1 = new ArrayList<>();
        List<String> strings2 = Arrays.asList("one", "two", "three");
        List<String> strings3 = List.of("one", "two", "three");

        System.out.println("Canonical name 1: " + strings1.getClass().getCanonicalName());
        System.out.println("Canonical name 2: " + strings2.getClass().getCanonicalName());
        System.out.println("Canonical name 3: " + strings3.getClass().getCanonicalName());

        // Simple name of a class
        System.out.println("Simple name: " + stringClass.getSimpleName());

        // Anonymous class
        Object key = new Object() {};
        System.out.println("Anonymous class simple name: " + key.getClass().getSimpleName());
        System.out.println("Anonymous class canonical name: " + key.getClass().getCanonicalName());

        // Lambda expression
        System.out.println("Lambda class simple name: " + supplier.getClass().getSimpleName());
        System.out.println("Lambda class canonical name: " + supplier.getClass().getCanonicalName());

        // Type name
        System.out.println("Type name: " + stringClass.getTypeName());
        System.out.println("Anonymous class type name: " + key.getClass().getTypeName());
        System.out.println("Lambda class type name: " + supplier.getClass().getTypeName());

        // Getting a class name with getName()
        System.out.println("Class name: " + stringClass.getName());

        // Arrays naming convention
        int[] intArray = new int[0];
        Class<?> intArrayClass = intArray.getClass();
        System.out.println("Simple name (array): " + intArrayClass.getSimpleName());
        System.out.println("Type name (array): " + intArrayClass.getTypeName());
        System.out.println("Canonical name (array): " + intArrayClass.getCanonicalName());
        System.out.println("Name (array): " + intArrayClass.getName());

        long[][] bidiLongs = new long[0][];
        System.out.println("Multidimensional array name: " + bidiLongs.getClass().getName());

        String[][] bidiStrings = new String[0][];
        System.out.println("String multidimensional array name: " + bidiStrings.getClass().getName());

        // Getting a class from its encoded name
        Class<?> classFromEncodedName = Class.forName("[[Ljava.lang.String;");
        System.out.println("Decoded class: " + classFromEncodedName + "\n");

        System.out.println("************** Modifiers *********+ \n");
        // Exemple avec la classe String
        Class<?> s = String.class;
        int modifiers = s.getModifiers();
        System.out.println("Is String final? " + Modifier.isFinal(modifiers));
        System.out.println("Is String abstract? " + Modifier.isAbstract(modifiers));

        // Exemple avec l'interface Collection
        Class<?> c = Collection.class;
        modifiers = c.getModifiers();
        System.out.println("Is Collection final? " + Modifier.isFinal(modifiers));
        System.out.println("Is Collection abstract? " + Modifier.isAbstract(modifiers));


        ////// ***************   Reading and Writing Fields    *********************


        Class<?> userClass = SecurityProperties.User.class;

// 1. Listing declared fields (all fields in the class, including private ones)
        Field[] declaredFields = userClass.getDeclaredFields();
        System.out.println("Declared Fields:");
        for (Field field : declaredFields) {
            System.out.println("- " + field.getName() + " (Modifiers: " + Modifier.toString(field.getModifiers()) + ", Type: " + field.getType().getSimpleName() + ")");
        }

        // 2. Listing only public fields (including inherited ones)
        Field[] publicFields = userClass.getFields();
        System.out.println("\nPublic Fields:");
        for (Field field : publicFields) {
            System.out.println("- " + field.getName() + " (Type: " + field.getType().getSimpleName() + ")");
        }

        // 4. Modifiers of a field
        System.out.println("\nField Modifiers:");
        System.out.println("isPrivate: " + Modifier.isPrivate(modifiers));
        System.out.println("isPublic: " + Modifier.isPublic(modifiers));
        System.out.println("isFinal: " + Modifier.isFinal(modifiers));


////// ******* working with arrays **********
        int length = 10;
        System.out.println(" working with arrays \n");
        Object o = Array.newInstance(int.class, 10);
        boolean isArray = o.getClass().isArray();
        System.out.println("isArray = " + isArray);
        Class<?> componentType = o.getClass().getComponentType();
        System.out.println("componentType = " + componentType);
        int reflectiveLength = Array.getLength(o);
        System.out.println("reflectiveLength = " + reflectiveLength);

        for (int i = 0; i < reflectiveLength; i++) {
            Array.set(o, i, 2*i);
        }
        System.out.println(Arrays.toString((int[])o));


////// reading annotations
        Class<?> clazz = Person.class;

        // Vérifier si @Bean est présente
        boolean isBean = clazz.isAnnotationPresent(Bean.class);
        System.out.println("isBean = " + isBean);

        // Récupérer toutes les annotations de la classe
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("Annotation trouvée: " + annotation);
        }

        // Lire l'annotation @Serialized et son attribut "format"
        Serialized serializedAnnotation = clazz.getAnnotation(Serialized.class);
        if (serializedAnnotation != null) {
            System.out.println("Format de sérialisation: " + serializedAnnotation.format());
        }

        ////// ******* testing the interceptor method


        System.out.println("********** testing the interceptor : *******");

        // Création de l'instance du service
        SimpleService service = new SimpleService();

        // Appel de la méthode sayHello avec l'intercepteur
        InterceptorManager.invoke(service, "sayHello");
    }
}