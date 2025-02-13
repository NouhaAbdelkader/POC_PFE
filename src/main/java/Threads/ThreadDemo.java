package Threads;

public class ThreadDemo extends Thread {
    public void run() {
        System.out.println("Thread en cours d'exécution : " + Thread.currentThread().getName());
    }
    public static void main(String[] args) {
        ThreadDemo thread1 = new ThreadDemo();
        thread1.start(); // Lance le thread
    }
}