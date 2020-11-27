package start.first.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    //테스트용 AppConfig
    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

    @Test
    void statefulServiceSingleton(){


        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
       
        //TreadA: A사용자가 10,000원 주문
        statefulService1.order("userA",10000);

        //TreadB: B사용자가 20,000원 주문
        statefulService2.order("userB",20000);

        //TreadA: 사용자A가 주문한 금액 조회
        int price = statefulService1.getPrice();
        //B사용자가 주문하면서 price가 20000으로 바뀌어 10000이 아닌 20000이 출력된다.
        System.out.println("price = " + price);  //price = 20000

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

}