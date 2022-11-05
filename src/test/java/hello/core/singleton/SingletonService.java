package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() { // constructor를 private으로 선언하므로써 외부에서 객체 생성을 할 수 없게 함
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
