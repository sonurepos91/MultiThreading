public class SynchronizedExchanger implements Runnable {

    public static void main (String[] args) {
        Thread t1 = new Thread(new SynchronizedExchanger(), "T1");
        t1.start();

        Runnable rt = () -> {
            System.out.println("Thread " + Thread.currentThread().getName());

        };

        Thread t2 = new Thread(rt, "T2");
        t2.start();


    }

    @Override
    public void run () {
        System.out.println("Thread " + Thread.currentThread().getName());
    }


}
