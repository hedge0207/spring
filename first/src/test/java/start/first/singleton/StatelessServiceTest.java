package start.first.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


class StatelessServiceTest {

    //테스트용 AppConfig
    static class TestConfig{
        @Bean
        public StatelessService statelessService(){
            return new StatelessService();
        }
    }

    @Test
    void statelessServiceSingleton(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(StatelessServiceTest.TestConfig.class);
        StatelessService statelessService1 = ac.getBean(StatelessService.class);
        StatelessService statelessService2 = ac.getBean(StatelessService.class);

        //아래와 같이 가격을 각각 userAPrice, userBPrice 라는 지역 변수에 담는다.
        //TreadA: A사용자가 10,000원 주문
        int userAPrice = statelessService1.order("userA",10000);

        //TreadB: B사용자가 20,000원 주문
        int userBPrice = statelessService2.order("userB",20000);

        //A사용자가 주문한 금액
        System.out.println("price = " + userAPrice); //10000

        Assertions.assertThat(userAPrice).isEqualTo(10000);

    }

}