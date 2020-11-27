package start.first.singleton;

public class SingletonService {

    //클래스 자체를 클래스 내부에 private static으로 선언
    private static final SingletonService instance = new SingletonService();

    //이제부터 호출 될 때 계속 새로운 객체를 생성하는 것이 아니라 위에서 생성한 객체를 넘기게 된다.
    //public으로 열어서 객체 인스턴스가 필요하면 오직 이 static 메서드를 통해서만 조회하도록 한다.
    public static SingletonService getInstance(){
        return instance;
    }

    //위에서 객체를 공유하도록 코드를 짰는데 다른 곳에서 다시 아래와 같이 쓰면 또 객체가 계속 생성되므로 다른 곳에서는 쓰지 못하게 해야 한다.
    //다른 곳에서 다시 SingletonService의 객체를 생성하지 못하도록 private을 걸어둔다.
    //SingletonService singletonService = new SingletonService(); 와 같은 코드를 이제 다른 곳에서는 쓸 수 없다.
    private SingletonService(){
    }
    
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
