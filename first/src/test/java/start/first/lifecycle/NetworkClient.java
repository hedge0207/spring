package start.first.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//InitializingBean(연결이 시작될 때 사용), DisposableBean(연결이 끊어질 때 사용)을 상속 받는다.
//public class NetworkClient implements InitializingBean, DisposableBean {  //인터페이스를 사용할 때의 코드

public class NetworkClient{
    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메세지");
    }

    public void setUrl(String url){
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect " + url);
    }
    
    //연결이 된 상태
    public void call(String message){
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close " + url);
    }

    //--------------------------인터페이스를 사용할 때의 코드---------------------------------
//    @Override
//    //InitializingBean에 있는 메서드를 상속받아 사용하는 것이다.
//    //의존관계 주입이 끝나면 호출할 메서드
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet 호출");
//        connect();
//        call("초기화 연결 메세지");
//    }
//
//    @Override
//    //DisposableBean에 있는 메서드를 상속받아 사용하는 것이다.
//    //연결이 끝어지면 호출할 메서드
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy 호출");
//        disconnect();
//    }
    //------------------------------------------------------------------------------------

    //-------------------------빈 등록 초기화, 소멸 메서드 사용--------------------------------
//    public void init() throws Exception {
//        System.out.println("NetworkClient.init 호출");
//        connect();
//        call("초기화 연결 메세지");
//    }
//
//    public void close() throws Exception {
//        System.out.println("NetworkClient.close 호출");
//        disconnect();
//    }
    //-----------------------------------------------------------------------------------

    //--------------------------------어노테이션 사용 방법-----------------------------------
    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.init 호출");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close 호출");
        disconnect();
    }
    //------------------------------------------------------------------------------------
}
