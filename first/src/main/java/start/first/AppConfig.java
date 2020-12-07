package start.first;

import start.first.discount.DiscountPolicy;
import start.first.discount.FixDiscountPolicy;
import start.first.discount.RateDiscountPolicy;
import start.first.member.MemberService;
import start.first.member.MemberServiceImpl;
import start.first.member.MemoryMemberRepository;
import start.first.order.OrderService;
import start.first.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService(){
        //MemoryMemberRepository를 MemberServiceImpl이 넣어주는 것이 아니라 여기서 넣어준다.
        //생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(),discountPolicy());
//        return null;
    }

    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
