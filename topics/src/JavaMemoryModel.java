public class JavaMemoryModel implements Runnable {

    private Integer counter = 0;
    private MyObject object = null;

    public JavaMemoryModel () {
    }

    public JavaMemoryModel (MyObject object) {
        this.object = object;
    }

    @Override
    public void  run () {
        for (int i = 0; i < 10000; i++) {
            this.counter++;
        }
        System.out.println(this.counter + " Thread Name " + Thread.currentThread().getName());
    }

    public static void main (String[] args) throws InterruptedException {
        MyObject object1 = new MyObject();
        Runnable runnable1 = new JavaMemoryModel(object1);
        Runnable runnable2 = new JavaMemoryModel(object1);
        Thread t1 = new Thread(runnable1, "Thread1");
        Thread t2 = new Thread(runnable2, "Thread2");

        t1.start();
        t2.start();
    }
}
