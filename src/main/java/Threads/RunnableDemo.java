package Threads;

public class RunnableDemo implements Runnable{


    /**
     * Dans un programme classique, tout s’exécute séquentiellement (une ligne après l’autre).
     * Avec les threads, on peut exécuter plusieurs tâches en parallèle, ce qui améliore les performances
     * et la réactivité d'une application.
     */

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
            try {
                Thread.sleep(1000); // Pause de 1 seconde
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

 /// lancer des threads en //
    public static void main(String[] args) {
        Thread t1 = new Thread(new RunnableDemo(), "Thread-1");
        Thread t2 = new Thread(new RunnableDemo(), "Thread-2");

        t1.start();
        t2.start();


    ///// Java 21 introduit les threads virtuels, qui sont plus légers que les threads traditionnels.
     }

}