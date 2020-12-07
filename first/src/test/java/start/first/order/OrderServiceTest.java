package start.first.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import start.first.AppConfig;
import start.first.member.Grade;
import start.first.member.Member;
import start.first.member.MemberService;
import start.first.member.MemberServiceImpl;

public class OrderServiceTest {

    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    OrderService orderService = appConfig.orderService();

    //MemberService memberService = new MemberServiceImpl();
    //OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"item1",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
