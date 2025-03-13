package 多线程;

public class Ticket {

    static class Task{
        public static int count = 0 ;

        public void sale(){
            while(true){
                synchronized (this.getClass()) {
                    if(count > 200) {
                        System.out.println("票已售完");
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + "卖票第" + count ++);
                }
            }
        }

    }

    public static void main(String[] args) {
        Task task = new Task();
        new Thread(task::sale,"窗口1").start();
        new Thread(task::sale,"窗口2").start();
        new Thread(task::sale,"窗口3").start();
    }
}
