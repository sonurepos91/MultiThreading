import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorTaskWithCallableInvokeAll implements Callable<String> {

    public static void main (String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // Make List of Callables<V>
        List<Callable<String>> callables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Callable<String> callable = new ExecutorTaskWithCallableInvokeAll();
            callables.add(callable);
        }

        List<Future<String>> results = new ArrayList<>();
        try {
            results = executorService.invokeAll(callables);
        } catch (InterruptedException exc) {
            throw new RuntimeException(exc);
        }

        results.stream().forEach(result -> {
            try {
                String output = result.get(1000, TimeUnit.MILLISECONDS);
                System.out.println("Output From Future : " + output);
            } catch (InterruptedException | ExecutionException | TimeoutException exc) {
                throw new RuntimeException(exc);
            }
        });

        executorService.shutdown();
        if (!executorService.isTerminated()) {
            try {
                executorService.awaitTermination(5000, TimeUnit.MILLISECONDS);
                executorService.shutdownNow();
            } catch (InterruptedException exc) {
                throw new RuntimeException(exc);
            }
        }

    }

    @Override
    public String call () {
        return Thread.currentThread().getName();
    }
}
