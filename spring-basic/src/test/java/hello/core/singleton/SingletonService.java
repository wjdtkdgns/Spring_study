package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    } // static이라 유일

    private SingletonService(){
    } // constructor를 private으로 함으로써 객체 인스턴스 생성막음

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
