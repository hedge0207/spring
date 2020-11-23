package start.first.beanfind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import start.first.discount.DiscountPolicy;
import start.first.discount.FixDiscountPolicy;
import start.first.discount.RateDiscountPolicy;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    //DiscountPolicy타입으로 조회했을 때 자식 타입인 RateDiscountPolicy와 FixDiscountPolicy가 조회되게 하기 위한 Config를 작성
    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 중복 에러가 발생")
    void findBeanByParentTypeDuplicate() {
        //아래 코드를 실행하면 NoUniqueBeanDefinitionException 에러가 발생한다.
        //DiscountPolicy bean = ac.getBean(DiscountPolicy.class);

        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 자식이 둘 이상 있으면 빈 이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 자식이 둘 이상 있으면 특정 자식 타입으로 조회해도 된다.")
    //현재 Config에는 부모 타입의 자식 타입 중 중복된 것이 없어 상관이 없으나 자식 타입 중 중복된 것이 있을 경우 에러가 발생할 수 있다.
    //따라서 좋은 방법은 아니다.
    void findBeanBySubType(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for(String key : beansOfType.keySet()){
            System.out.println("key = " + key +" value = " + beansOfType.get(key));
        }
    }
    
    @Test
    @DisplayName("Object 타입으로 조회해보기")
    //Object는 자바의 모든 객체의 부모이므로 모든 빈이 다 조회된다.
    void findAllBeanByObjectType(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for(String key : beansOfType.keySet()){
            System.out.println("key = " + key +" value = " + beansOfType.get(key));
        }
    }

}




