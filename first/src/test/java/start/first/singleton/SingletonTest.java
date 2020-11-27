package start.first.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import start.first.AppConfigSpring;
import start.first.member.MemberService;

public class SingletonTest {
    
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        //
        AppConfigSpring appConfigSpring = new AppConfigSpring();
        
        //한 유저가 조회, 호출될 때마다 MemberService의 객체를 생성
        MemberService memberService1 = appConfigSpring.memberService();

        //다른 유저가 조회, 호출될 때마다 MemberService의 객체를 생성
        MemberService memberService2 = appConfigSpring.memberService();

        //두 객체의 참조값이 다르다. 즉 매번 새로 생성한다.
        System.out.println("memberService1 = " + memberService1); //memberService1 = start.first.member.MemberServiceImpl@5542c4ed
        System.out.println("memberService2 = " + memberService2); //memberService2 = start.first.member.MemberServiceImpl@1573f9fc
        
        //정말 둘이 다른지 검증
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singtonServiceTest(){
        //SingletonService에 private을 걸어뒀으므로 아래와 같이 다른 곳에서 새로 생성 할 수 없다.
        //new SingletonService();
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        //둘의 참조값이 동일한다.
        System.out.println("singletonService1 = " + singletonService1); //singletonService1 = start.first.singleton.SingletonService@1165b38
        System.out.println("singletonService2 = " + singletonService2); //singletonService2 = start.first.singleton.SingletonService@1165b38

        //둘이 정말 동일한지 검증
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
        //.isSameAs: 자바 ==으로 비교하는 것과 동일
        //.isEqualTo: 자바의 .equal 메소드로 비교하는 것과 동일
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        
        //스프링 컨테이너 생성
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigSpring.class);
        
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        //둘의 참조값이 동일한다.
        System.out.println("memberService1 = " + memberService1); //memberService1 = start.first.member.MemberServiceImpl@23fb172e
        System.out.println("memberService2 = " + memberService2); //memberService2 = start.first.member.MemberServiceImpl@23fb172e

        //둘이 정말 동일한지 검증
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }

}
