package Threads;
import java.util.ArrayList;
import java.util.List;

public class SynchronizedThreadExample {
    /**
     Cet exemple de partage de données entre threads est utile lorsque plusieurs
     threads effectuent des opérations sur une ressource partagée,
     */
    public static void main(String[] args) {
        // Liste partagée entre plusieurs threads
        List<Integer> sharedList = new ArrayList<>();

        // Lancer plusieurs threads qui ajoutent des éléments dans la liste
        Thread thread1 = new Thread(new AddToListTask(sharedList));
        Thread thread2 = new Thread(new AddToListTask(sharedList));
        Thread thread3 = new Thread(new AddToListTask(sharedList));

        thread1.start();
        thread2.start();
        thread3.start();

        // Attendre que tous les threads finissent leur travail
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Afficher la liste finale
        System.out.println("Final shared list: " + sharedList);
    }
}

class AddToListTask implements Runnable {
    private final List<Integer> sharedList;

    public AddToListTask(List<Integer> sharedList) {
        this.sharedList = sharedList;
    }

    @Override
    public void run() {
        synchronized (sharedList) {
            // Ajouter des éléments dans la liste partagée
            for (int i = 0; i < 5; i++) {
                sharedList.add(i);
                System.out.println(Thread.currentThread().getName() + " added " + i);
                try {
                    Thread.sleep(100); // Simuler un délai
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
