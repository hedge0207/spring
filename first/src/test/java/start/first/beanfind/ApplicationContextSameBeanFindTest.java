package start.first.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import start.first.member.MemberRepository;
import start.first.member.MemoryMemberRepository;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {


    //기존의 AppConfigSpring에는 동알한 타입이 없으므로 SameBeanConfig를 새로 만든다.
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    //동일한 타입을 만들기 위한 Config 생성
    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }

    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate(){
        //아래와 같이 실행할 경우 MemoryMemberRepository 타입에 해당하는 빈이 2개 있으므로 NoUniqueBeanDefinitionException error가 발생
        //MemberRepository memberRepository = ac.getBean(MemberRepository.class);

        //따라서 assertThrows를 사용한다.
        assertThrows(NoUniqueBeanDefinitionException.class,()->ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository1",MemberRepository.class);
        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("같은 타입이 둘 이상 있을 때 특정 타입을 모두 조회하기기")
    void findAllBeanByType(){
        //getBeansOfType을 사용
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for(String key : beansOfType.keySet()){
            System.out.println("key = " + key +" value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        //SameBeanConfig에서 생성한 빈이 2개이므로 beansOfType에 2개가 들어 있다면 성공한 것이다.
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }




}
