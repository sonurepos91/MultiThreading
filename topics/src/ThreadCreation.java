import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public class ThreadCreation {

    public static void main (String[] args) {
        Thread t1 = new Thread(new ThreadClass(), "Thread 1");
        Thread t2 = new Thread(new ThreadClass2(), "Thread 2");
        Callable<String> callable = new ThreadClass3();
        FutureTask<String> futureTask = new FutureTask<>(callable);

        Thread t4 = new Thread(futureTask, "Thread 4");
        t1.start();
        t2.start();
        t4.start();

        Runnable runnable = () -> {
            System.out.println("Thread started");
            String name = Thread.currentThread().getName();
            System.out.println("Thread Name : " + name);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread completed 3");
        };

        Thread t3 = new Thread(runnable, "Thread 3");
        t3.setDaemon(true);
        t3.start();

        try {
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public static class ThreadClass extends Thread {

        public void run () {
            System.out.println("Thread started");
            String name = Thread.currentThread().getName();
            System.out.println("Thread Name : " + name);
            System.out.println("Thread completed");
        }

    }

    public static class ThreadClass2 implements Runnable {

        @Override
        public void run () {
            System.out.println("Thread started");
            String name = Thread.currentThread().getName();
            System.out.println("Thread Name : " + name);
            System.out.println("Thread completed");
        }
    }

    public static class ThreadClass3 implements Callable<String> {

        @Override
        public String call () throws Exception {
            System.out.println("Thread Name : " + Thread.currentThread().getName());
            return "Hello";
        }
    }
}
