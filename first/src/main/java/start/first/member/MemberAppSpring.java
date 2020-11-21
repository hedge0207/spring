package start.first.member;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import start.first.AppConfigSpring;

public class MemberAppSpring {

    public static void main(String[] args) {

        //ApplicationContext는 컨테이너라고 보면 된다.
        //AnnotationConfigApplicationContext는 @Configuration 어노테이션이 달린 클래스를 관리하기 위해 쓰는 것이고 인자로 구성 정보가 담긴 클래스를 받는다.
        //아래 코드가 실행되면 객체들이 생성되어 컨테이너에 등록되고 관리되게 된다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfigSpring.class);
        //getBean은 컨테이너에 등록된 Bean을 가져오는 것으로 첫 번째 인자로 메서드 이름, 두 번째 인자로 타입을 받는다.
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);

        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);

        //회원 조회
        Member findMember = memberService.findMember(1L);
        System.out.println("join member: " + member.getName());      //join member: memberA
        System.out.println("find member: " + findMember.getName());  //find member: memberA
    }
}
