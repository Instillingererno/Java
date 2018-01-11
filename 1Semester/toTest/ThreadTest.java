class ThreadTest {
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            ThreadObject test = new ThreadObject();
            test.start();
        }
    }
}

class ThreadObject extends Thread {
    public void run() {
        System.out.println("It worked");
    }
}
