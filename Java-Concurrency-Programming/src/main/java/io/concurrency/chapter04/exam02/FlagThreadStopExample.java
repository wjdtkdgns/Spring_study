package io.concurrency.chapter04.exam02;

public class FlagThreadStopExample {
    // volatile 키워드 추가
   volatile boolean running = true;
//    boolean running = true;

    public void volatileTest() {
        new Thread(() -> {
            int count = 0;
            while (running) {
                /*try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }*/
                // 컨텍스트 스위칭 되면서 아래 쓰레드의 캐싱된 정보가 메모리에 저장될 수 있다
                // 이때, 컨텍스트 스위칭되며 캐시가 초기화 되었기 때문에, 메모리 값을 읽게 된다
                // 그러면 running은 false 이므로 무한 루프가 멈춘다
                // 할 수는 있는데, 그냥 atomic, volatile 사용해라
                count++;
            }
            System.out.println("Thread 1 종료. Count: " + count);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            System.out.println("Thread 2 종료 중..");
            running = false;
        }).start();
    }

    public static void main(String[] args) {
        new FlagThreadStopExample().volatileTest();
    }
}
