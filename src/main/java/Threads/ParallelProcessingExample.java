package Threads;
import java.util.ArrayList;
import java.util.List;

public class ParallelProcessingExample {
    public static void main(String[] args) {
        // Liste de nombres à traiter
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Liste de threads pour traiter chaque nombre
        List<Thread> threads = new ArrayList<>();

        // Lancer un thread pour chaque nombre dans la liste
        for (Integer number : numbers) {
            Thread thread = new Thread(new SquareTask(number));
            threads.add(thread);
            thread.start();
        }

        // Attendre que tous les threads se terminent
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All tasks completed.");
    }
}

class SquareTask implements Runnable {
    private final int number;

    public SquareTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        // Calcul du carré du nombre et affichage
        int square = number * number;
        System.out.println(Thread.currentThread().getName() + " - Square of " + number + " is " + square);
    }
}
