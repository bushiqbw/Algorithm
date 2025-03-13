package 多线程;

public class TakeTurnsPrint2{

    static class Task{
        public static boolean flag = true;

        public synchronized void printNum(){
            int cnt = 0;
            for(int i = 0; i < 26; i ++){
                while(!flag){
                    try{
                        wait();
                    }catch (InterruptedException e){}
                }
                System.out.print(++cnt);
                System.out.print(++cnt);
                flag = !flag;
                notify();
            }
        }

        public synchronized void printChar(){
            int cnt = 0;
            for(int i = 0; i < 26; i ++){
                while(flag){
                    try{
                        wait();
                    }catch (InterruptedException e){}
                }
                char ch = (char)('A' + cnt++);
                System.out.print(ch);
                flag = !flag;
                notify();
            }
        }

    }


    public static void main(String[] args) {
        Task task = new Task();
        new Thread(() -> {
            try{
                task.printNum();
            }catch (Exception e){
            }
        }).start();

        new Thread(() -> {
            try{
                task.printChar();
            }catch (Exception e){
            }
        }).start();
    }
}
