public class SharedResourceWithoutRaceConditions implements Runnable{

    private int count =0;

    @Override
    public synchronized void  run () {
            for (int i = 0; i < 1000; i++) {
                count++;
            }
        System.out.println(Thread.currentThread().getName() + " Thread Count " + count);
    }

    public static void main(String[] args){
        SharedResourceWithoutRaceConditions sharedResourceWithoutRaceConditions = new SharedResourceWithoutRaceConditions();
        Thread t1 = new Thread(sharedResourceWithoutRaceConditions);
        Thread t2 = new Thread(sharedResourceWithoutRaceConditions);


        Runnable runnable =()->{
            System.out.println("Thread Name : " + Thread.currentThread().getName());
        };

        Thread t3 = new Thread(runnable ,"T5");

        t1.start();
        t2.start();
        t3.start();

    }
}
