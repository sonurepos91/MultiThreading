public class OddEvenNumUsingThread {
    private int count = 1;

    public static void main (String[] args) {
        OddEvenNumUsingThread obj = new OddEvenNumUsingThread();
        int max = 51;
        Runnable runnable = () -> {
            obj.printEvenNumbers(max);
        };
        Runnable runnable1 = () -> {
            obj.printOddNumbers(max);
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable1);

        t1.start();
        t2.start();
    }

    private void printOddNumbers (int max) {
        synchronized (this) {
            while (count < max) {
                if (count % 2 != 0) {
                    System.out.println(Thread.currentThread().getName() + "  Odd Number " + count);
                    try {
                        wait();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                count++;
                notify();
            }
        }
    }

    private void printEvenNumbers (int max) {
        synchronized (this) {
            while (count < max) {
                if (count % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + " Even Number " + count);
                    try {
                        wait();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                count++;
                notify();
            }
        }
    }


}
