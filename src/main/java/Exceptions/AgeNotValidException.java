package Exceptions;

class AgeNotValidException extends Exception { // Hérite bien de Exception
    public AgeNotValidException(String message) {
        // super() est utilisé pour appeler le constructeur de la classe parente (la classe dont hérite la classe actuelle).
        super(message); // Appel du constructeur de Exception
    }
}