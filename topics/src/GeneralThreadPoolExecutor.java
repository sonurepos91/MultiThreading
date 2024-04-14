import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GeneralThreadPoolExecutor implements Runnable {

    private String message;

    GeneralThreadPoolExecutor (String message) {
        this.message = message;
    }

    @Override
    public void run () {
        System.out.println("Thread " + Thread.currentThread().getName() + " " + message);
    }

    public static void main (String args[]) {

        int corePoolSize = 1;
        int maximumPoolSize = 1;
        long keepAliveTime = 1000;

        ExecutorService executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100000));

        for (int i = 0; i < 100000; i++) {
            executorService.execute(new GeneralThreadPoolExecutor("Task" + i));
        }

        List<Runnable> runnables = new ArrayList<>();
        executorService.shutdown();
        if (!executorService.isTerminated()) {
            try {
                boolean isExecutorServicesTermianted = executorService.awaitTermination(100, TimeUnit.MILLISECONDS);
                System.out.println("Executor Service Terminated " + isExecutorServicesTermianted);
                runnables = executorService.shutdownNow();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        System.out.println("List Of Tasks could not complete");
        runnables.forEach(r2 -> {
            System.out.println(r2);
        });
    }
}
