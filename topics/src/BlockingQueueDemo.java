import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {

    public static void main (String[] args) {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1000);

        for (int i = 0; i < 10; i++) {
            try {
                queue.put(newRunnable("Task T" + i)); // add method gives Queue Full Exception
            }catch(InterruptedException ex){
                throw new RuntimeException(ex);
            }
        }

        //ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 1000, TimeUnit.MILLISECONDS, queue);

        while (!queue.isEmpty()) {
            System.out.println("Queue Top Element " + queue.peek()); // Does not check if Queue is Empty
            Runnable remove = queue.remove();
            System.out.println("Element removed " + remove);
        }
    }

    private static Runnable newRunnable (String s) {
        return new Runnable() {
            @Override
            public void run () {
                System.out.println("Task " + s);
            }
        };
    }
}
