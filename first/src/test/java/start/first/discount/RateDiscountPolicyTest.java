package start.first.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import start.first.member.Grade;
import start.first.member.Member;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();
    
    //성공 테스트
    @Test
    //JUnit5에서 제공하는 기능으로 터미널 창에 아래 작성한 내용으로 테스트명이 출력된다.
    @DisplayName("VIP는 10% 할인이 적용되어야 합니다.")
    void dis(){
        //given
        Member member = new Member(1L,"memberVIP", Grade.VIP);

        //when
        int discount = discountPolicy.discount(member,10000);

        //then
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    //실패 테스트
//    @Test
//    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
//    void dontDis(){
//        //given
//        //VIP가 아닌 BASIC으로 생성
//        Member member = new Member(2L,"memberBASIC", Grade.BASIC);
//
//        //when
//        int discount = discountPolicy.discount(member,10000);
//
//        //then
//        //VIP가 아니므로 1000원이 아닌 0원이 되어야 한다.
//        Assertions.assertThat(discount).isEqualTo(1000);
//    }
}