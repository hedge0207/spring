package start.first.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL){
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init(){
        //java에서 제공하는 UUID가 있다. 유니크한 아이디를 만들기 위해 사용하며, 아이디가 겹칠 확률은 로또에 연속 당첨될 확률보다 낮다.
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "]" + " request scope bean create: " + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "]" + " request scope bean close: " + this);
    }
}
