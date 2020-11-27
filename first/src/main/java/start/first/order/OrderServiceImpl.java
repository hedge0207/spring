package start.first.order;

import org.springframework.beans.factory.annotation.Autowired;
import start.first.discount.DiscountPolicy;
import start.first.discount.FixDiscountPolicy;
import start.first.discount.RateDiscountPolicy;
import start.first.member.Member;
import start.first.member.MemberRepository;
import start.first.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    //기존 코드
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //변경한 코드
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //생성자
    //@Autowired를 사용하기 전 코드
    //public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    //    this.memberRepository = memberRepository;
    //    this.discountPolicy = discountPolicy;
    //}

    //생성자에 @Autowired를 붙인다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }




    //싱글톤이 지켜지는지 테스트용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
