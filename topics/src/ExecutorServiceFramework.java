import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceFramework {

    public static void main (String[] args) {

        //Creating Executor Service by the help of Executore
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();

        //execute() method takes only the runnable Interface
        executorService.execute(runnable("Task-1-1"));
        executorService.execute(runnable("Task-1-2"));
        executorService.execute(runnable("Task-1-3"));
        executorService.execute(runnable("Task-1-3"));
        executorService.execute(runnable("Task-1-3"));


        // Proper Way to Close Executor Pool
        executorService.shutdown();
        if (!executorService.isTerminated()) {
            try {
                executorService.awaitTermination(5000, TimeUnit.MILLISECONDS);
                executorService.shutdownNow();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private static Runnable runnable (String msg) {
        return new Runnable() {
            @Override
            public void run () {
                System.out.println("Thread Name : " + Thread.currentThread().getName() + " " + msg);
            }
        };
    }
}
