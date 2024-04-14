public class RaceCondition implements Runnable{

    private  int count ;

    public RaceCondition(int count){
        this.count=count;
    }

    @Override
    public void run(){
        for(int i=0;i<10000;i++){
            count++;
        }
        System.out.println("Thread Name : " + Thread.currentThread().getName() + " count : " + count);
    }

    public static void main(String args[]){
        Runnable runnable = new RaceCondition(0);

        new Thread(runnable).start();
        new Thread(runnable).start();
    }

}
