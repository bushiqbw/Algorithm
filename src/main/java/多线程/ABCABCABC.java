package 多线程;

public class ABCABCABC {

    static class Task{
        public static  int flag = 0;
        public synchronized void print1(){
            for(int i = 0; i < 5; i ++){
                while(flag != 0){
                    try{
                        wait();
                    }catch(Exception e){
                    }
                }
                System.out.print("A");
                flag = (flag + 1) % 3;
                notifyAll();
            }
        }
        public synchronized void print2(){
            for(int i = 0; i < 5; i ++){
                while(flag != 1){
                    try{
                        wait();
                    }catch(Exception e){
                    }
                }
                System.out.print("B");
                flag = (flag + 1) % 3;
                notifyAll();
            }
        }
        public synchronized void print3(){
            for(int i = 0; i < 5; i ++){
                while(flag != 2){
                    try{
                        wait();
                    }catch(Exception e){
                    }
                }
                System.out.print("C");
                flag = (flag + 1) % 3;
                notifyAll();
            }
        }

    }

    public static void main(String[] args) {
        Task task = new Task();
        new Thread(() -> {
            task.print1();
        }).start();
        new Thread(() -> {
            task.print2();
        }).start();
        new Thread(() -> {
            task.print3();
        }).start();
    }
}
