import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceCallable {

    public static void main(String [] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<String>> allResults = new ArrayList<>();

        for(int i=0;i< 9999999;i ++){
            //subimt() method takes both Runnable and Callable
            //Callable Returns Future Object
            // We can get the result from Future by : get() method
            Future<String> submit = executorService.submit(newCallableTask("Task Name " + i));
            allResults.add(submit);
        }

        allResults.stream().forEach(result->{
            try {
                String output = result.get();
                System.out.println(output);
            }catch(InterruptedException | ExecutionException ex){
                throw new RuntimeException(ex);
            }
        });

        List<Runnable> runnables = new ArrayList<>();
        executorService.shutdown();
        if(!executorService.isTerminated()){
            try {
                executorService.awaitTermination(0, TimeUnit.MILLISECONDS);
                runnables = executorService.shutdownNow();
            }catch(InterruptedException ex){
                throw new RuntimeException(ex);
            }
        }

        runnables.forEach(runnable->{
            System.out.println("Thread not Terminated " + runnable);
        });



    }

    private static Callable<String> newCallableTask(String taskNumber){
        return () -> Thread.currentThread().getName() + taskNumber;
    }
}
