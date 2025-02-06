package Inheritance;

public class Horse {
    public String identifyMyself() {
        return "I am a horse.";
    }
}

interface Flyer {
    default public String identifyMyself() {
        return "I am able to fly.";
    }
}

 interface Mythical {
    default public String identifyMyself() {
        return "I am a mythical creature.";
    }
}

 class Pegasus extends Horse implements Flyer, Mythical {
    public static void main(String... args) {
        Pegasus myApp = new Pegasus();
        System.out.println(myApp.identifyMyself());
    }
}

// ici le but c'était de rappeler le fait que l'héritage de plusieurs méthode renvoie et suit les valeurs de supertypes

// remarque : Si deux ou plusieurs méthodes par défaut définies indépendamment entrent en conflit, ou si une méthode par défaut entre en conflit avec une méthode abstraite, le compilateur Java génère une erreur de compilation. Vous devez remplacer explicitement les méthodes de supertype.