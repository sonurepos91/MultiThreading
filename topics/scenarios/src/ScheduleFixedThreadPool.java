import java.util.List;
import java.util.concurrent.*;

public class ScheduleFixedThreadPool {

    public static void main (String[] args) throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

        Runnable rt = () -> {
            System.out.println("Hii..........");
        };

        executorService.scheduleAtFixedRate(rt, 100, 2000, TimeUnit.MILLISECONDS);

        executorService.awaitTermination(10000,TimeUnit.MILLISECONDS);
        executorService.shutdown();
        if (!executorService.isTerminated()) {
            try {
                executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
                List<Runnable> runnables = executorService.shutdownNow();
                runnables.forEach(runnable -> System.out.println(runnable));
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
