package 多线程;


/**
 * https://blog.csdn.net/shinecjj/article/details/103792151
 */
public class TakeTurnsPrint {

    public static class Task1{
        static boolean flag = false;
        static int count = 0;

        public synchronized void print1() throws InterruptedException {
            for(int i = 0; i < 50; i ++){
                    while(!flag) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " " +  ++count);
                    flag = !flag;
                    notify();
                }

        }
        public synchronized void print2() throws InterruptedException {
            for(int i = 0; i < 50; i ++){
                    while(flag) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " " +  ++count);
                    flag = !flag;
                    notify();
                }

        }
    }

    public static void main(String[] args) {
        Task1 task = new Task1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    task.print1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    task.print2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
