import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample {
    Lock lock1 = new ReentrantLock(true);
    Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args){

        DeadlockExample deadlockExample = new DeadlockExample();
        
        Thread t1 = new Thread(deadlockExample::operation1);
        Thread t2 = new Thread(deadlockExample::operation2);

        t1.start();
        t2.start();
    }

    private void operation2 () {
        lock1.lock();
        System.out.println("Lock1 acquired : waiting for lock2");

        lock2.lock();
        System.out.println("Lock2 acquired");

        lock2.unlock();
        lock1.unlock();
        System.out.println("Finished Operation 2");
    }

    private void operation1 () {

        lock2.lock();
        System.out.println("Lock2 acquired : waiting for lock1");

        lock1.lock();
        System.out.println("Lock1 acquired :");

        lock1.unlock();
        lock2.unlock();
        System.out.println("Finished Operation 1");
    }
}
