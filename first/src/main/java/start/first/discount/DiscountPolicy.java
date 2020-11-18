package start.first.discount;

import start.first.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);
}
