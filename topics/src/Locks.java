import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {

    public static void main (String[] args) {

        ReentrantLock lock= new ReentrantLock();
        Runnable runnable = () -> {
            lockSleepUnlock(lock, 1000);
        };
        Callable callable =()->{
            return lockSleeplock(lock, 1000);
        };

        Thread t1 = new Thread(runnable,"T1");
        Thread t2 = new Thread(runnable,"T2");
        Thread t3 = new Thread(runnable, "T3");

        t1.start();
        t2.start();
        t3.start();

    }

    private static String lockSleeplock (ReentrantLock lock, int i) {
        return "";
    }

    private static void lockSleepUnlock (ReentrantLock lock, int timeInMillis) {
        try {
            lock.lock();
            System.out.println("Thread " + Thread.currentThread().getName() + " holds the lock" + " lock count " + lock.getHoldCount());
            lock.lock();
            System.out.println("Thread " + Thread.currentThread().getName() + " holds the lock" + " lock count " + lock.getHoldCount());
        }finally {
            System.out.println("No Of Threads Waiting for aquiring this Lock " + lock.getQueueLength());
            lock.unlock();
            lock.unlock();
        }
    }
}

