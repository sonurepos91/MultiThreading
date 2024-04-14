import java.util.concurrent.Semaphore;

public class SemaphoresDemo {

    public static void main (String[] args) {
        Semaphore semaphore = new Semaphore(2, true);
        Runnable r1 = ()-> {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " acquires lock");
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }finally {
                System.out.println(Thread.currentThread().getName() + " releases lock");
                semaphore.release();

            }
        };
        new Thread(r1,"T1").start();
        new Thread(r1,"T2").start();
        new Thread(r1,"T3").start();
    }

}
