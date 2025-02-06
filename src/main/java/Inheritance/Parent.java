package Inheritance;

public class Parent {

    // Méthode statique
    public static void staticMethod() {
        System.out.println("Static method in Parent");
    }
    void speak() { System.out.println("Parent speaking"); }


    // Méthode d'instance
    public void instanceMethod() {
        System.out.println("Instance method in Parent");
    }
}

class Child extends Parent {
    // Masque la méthode statique de Parent
    public static void staticMethod() {
        System.out.println("Static method in Child");
    }
    @Override
    void speak() { System.out.println("Child speaking"); }

    // Remplace la méthode d'instance de Parent
    @Override
    public void instanceMethod() {
        System.out.println("Instance method in Child");
    }

    public static void main(String[] args) {
        // 1. Appel avec la classe Parent
        Parent.staticMethod(); // Affiche : Static method in Parent

        // 2. Objet Child référencé par Child
        Child child = new Child();
        child.staticMethod(); // Affiche : Static method in Child
        child.instanceMethod(); // Affiche : Instance method in Child

        // 3. Objet Child référencé par Parent (polymorphisme)
        Parent parentRef = new Child();
        parentRef.staticMethod(); // Affiche : Static method in Parent (masquage)
        parentRef.instanceMethod(); // Affiche : Instance method in Child (remplacement)


// pour comprendre la notion de polymorphisme
        Parent obj = new Child();
        obj.speak(); // Affiche "Child speaking"
    }

}
