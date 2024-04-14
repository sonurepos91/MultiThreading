public class Counter {

    private long count = 0;

    public  long incAndGet () {
        synchronized (this) {
            this.count++;
            return this.count;
        }
    }

    public  long getCount () {
        synchronized (this) {
            return this.count;
        }
    }
}
