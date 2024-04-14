import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditions {

    public static void main (String[] args) {

        Counter counter = new Counter(); // If want to use Counter only make it's method synchronized
        //AtomicInteger counter = new AtomicInteger(0); // Making the use of AtomicInteger will remove Synchronization Issue

        Runnable r1 = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.incAndGet();
            }
            System.out.println(Thread.currentThread().getName() + " " + counter.getCount());
        };

        Runnable r2 = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.incAndGet();
            }
            System.out.println(Thread.currentThread().getName() + " " + counter.getCount());
        };
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

    }
}
