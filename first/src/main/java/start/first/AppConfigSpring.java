package start.first;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import start.first.discount.DiscountPolicy;
import start.first.discount.FixDiscountPolicy;
import start.first.discount.RateDiscountPolicy;
import start.first.member.MemberService;
import start.first.member.MemberServiceImpl;
import start.first.member.MemoryMemberRepository;
import start.first.order.OrderService;
import start.first.order.OrderServiceImpl;

//구성 정보, 설정 정보라는 것을 알리기 위해 @Configuration 어노테이션을 달아 준다.
@Configuration
public class AppConfigSpring {

    //@Bean 어노테이션을 사용하면 스프링 컨테이너에 등록이 된다.
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService"); //호출 여부 확인을 위한 sout
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository"); //호출 여부 확인을 위한 sout
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService"); //호출 여부 확인을 위한 sout
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }
}