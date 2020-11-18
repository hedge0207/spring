package start.first.discount;

import start.first.member.Grade;
import start.first.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discoutFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        //enum은 ==을 사용하여 비교한다.
        if(member.getGrade()== Grade.VIP){
            return discoutFixAmount;
        }else{
            return 0;
        }

    }
}
