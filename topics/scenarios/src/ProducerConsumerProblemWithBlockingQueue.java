import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerProblemWithBlockingQueue {

    public static void main (String[] args) {

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    queue.put(("Task : " + i));// Wait if Queue Size is Full
                    System.out.println("Thread Produced " + "Task :" + i);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            for(int i=0;i<10;i++) {
                try {
                    String element = queue.take(); // Wait if Queue is Empty
                    System.out.println("Thread Consumed " + element);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Consumer ");

        producer.start();
        consumer.start();

    }

}


