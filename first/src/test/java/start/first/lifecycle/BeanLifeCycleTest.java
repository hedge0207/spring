package start.first.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        //close를 사용하기 위해서는 ConfigurableApplicationContext 타입이어야 한다.
        //ConfigurableApplicationContext는 ApplicationContext를 상속 받는다.
        //AnnotationConfigApplicationContext는 ConfigurableApplicationContext를 상속 받는다.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{

        //아래와 같이 Bean의 속성값으로 시작 메서드와 종료 메서드를 문자열로 지정해준다.
//        @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-word.dev");
            return networkClient;
        }
    }
}
