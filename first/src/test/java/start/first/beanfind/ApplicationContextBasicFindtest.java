package start.first.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import start.first.AppConfigSpring;
import start.first.member.MemberService;
import start.first.member.MemberServiceImpl;

public class ApplicationContextBasicFindtest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigSpring.class);

    @Test
    @DisplayName("빈 이름으로 조회하기")
    void findBeanByName(){
        //getBean은 빈 타입만 넘겨도 되고 빈 이름과 타입을 함께 넘겨도 된다.
        MemberService memberService = ac.getBean("memberService",MemberService.class);
        //memberService가 MemberService의 인스턴스면 성공
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        //findBeanByName과 달리 인터페이스인 MemberService가 아닌 구체인 MemberServiceImpl의 타입으로 조회한다.
        //인터페이스가 아닌 구체에 의존하기에 좋은 코드는 아니다. 그러나 필요한 때도 있으므로 구체 타입으로도 조회가 가능하다는 것만 알면 된다.
        MemberService memberService = ac.getBean("memberService",MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    //실패 테스트, 테스트는 항상 실패 테스트를 함께 만드는 것이 좋다.
    @Test
    @DisplayName("빈 이름으로 조회 실패")
    void findBeanByNameX(){
        //ac.getBean("xxxxx",MemberService.class)가 실행될 때 NoSuchBeanDefinitionException예외가 발생하면 성공
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx",MemberService.class));
    }
}
