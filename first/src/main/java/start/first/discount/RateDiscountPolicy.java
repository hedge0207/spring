package start.first.discount;

import org.springframework.stereotype.Component;
import start.first.annotation.MainDiscountPolicy;
import start.first.member.Grade;
import start.first.member.Member;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade()== Grade.VIP){
            return price*discountPercent/100;
        }else{
            return 0;
        }
    }
}
