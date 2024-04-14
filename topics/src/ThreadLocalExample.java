public class ThreadLocalExample{

    public static void main(String[] args){

        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        Runnable r1 = ()->{
            threadLocal.set("1");
            System.out.println("Thread T1 " +threadLocal.get());
        };

        Runnable r2 = ()->{
            threadLocal.set("2");
            //threadLocal.remove();
            System.out.println("Thread T2 " +threadLocal.get());
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
    }


}
