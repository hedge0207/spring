package start.first.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import start.first.AppConfigSpring;
import start.first.member.MemberRepository;
import start.first.member.MemberServiceImpl;
import start.first.order.OrderServiceImpl;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigSpring.class);

        //본래 아래와 같이 구체 타입(MemberServiceImpl, OrderServiceImpl)으로 빈을 꺼내는 것은 좋지 않은 방법이지만 편의를 위해 아래와 같이 꺼낸다.
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        //셋의 참조값이 같다.
        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        //memberService -> memberRepository1 = start.first.member.MemoryMemberRepository@299321e2
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        //orderService -> memberRepository2 = start.first.member.MemoryMemberRepository@299321e2
        System.out.println("memberRepository = " + memberRepository);
        //memberRepository = start.first.member.MemoryMemberRepository@299321e2

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }

    @Test
    void configurationDeepTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigSpring.class);
        AppConfigSpring bean = ac.getBean(AppConfigSpring.class);

        System.out.println("bean = " + bean.getClass());
        //bean = class start.first.AppConfigSpring$$EnhancerBySpringCGLIB$$31febfb1

    }
}
