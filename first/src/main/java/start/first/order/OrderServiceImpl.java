package start.first.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import start.first.annotation.MainDiscountPolicy;
import start.first.discount.DiscountPolicy;
import start.first.discount.FixDiscountPolicy;
import start.first.discount.RateDiscountPolicy;
import start.first.member.Member;
import start.first.member.MemberRepository;
import start.first.member.MemoryMemberRepository;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    //----------------------------------lombok을 통해 간소화한 생성자 의존관계 주입----------------------
//    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy;
    //---------------------------------------------------------------------------------------------

    //-----------------------------------생성자를 통한 의존관계 주입------------------------------------
    //기존 코드
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //변경한 코드
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //생성자
    //@Autowired를 사용하기 전 코드(사실 이 코드에는 @Autowired가 생략되어 있던 것이다)
    //public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    //    this.memberRepository = memberRepository;
    //    this.discountPolicy = discountPolicy;
    //}

    //생성자에 @Autowired를 붙인다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    //------------------------------------------------------------------------------------------------


    //-----------------------------------setter를 통한 의존관계 주입--------------------------------------
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy){
//        this.discountPolicy = discountPolicy;
//    }
    //--------------------------------------------------------------------------------------------------


    //---------------------------------필드주입을 통한 의존관계 주입-----------------------------------------
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;
    //--------------------------------------------------------------------------------------------------


    //--------------------------------일반 메서드 주입-----------------------------------------------------
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
    //---------------------------------------------------------------------------------------------------

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
