package Threads;
public class ThreadExample {
    public static void main(String[] args) {
        // Création de deux threads
        Thread t1 = new Thread(new Task(), "Thread-1");
        Thread t2 = new Thread(new Task(), "Thread-2");

        // Démarrage des threads
        t1.start();
        t2.start();

        try {
            // On attend la fin des deux threads avant de terminer le programme principal
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread finished.");
    }
}

class Task implements Runnable {
    @Override
    public void run() {
        // Chaque thread exécute cette méthode
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
            try {
                // Simuler une petite attente pour rendre l'exécution plus visible
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
