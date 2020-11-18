package start.first.order;

import start.first.AppConfig;
import start.first.member.Grade;
import start.first.member.Member;
import start.first.member.MemberService;
import start.first.member.MemberServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        //AppConfig 객체를 생성
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
        
        //기존 코드
        //MemberService memberService = new MemberServiceImpl();
        //OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"itemA",10000);

        System.out.println("order: " + order);
        System.out.println("calculate price: " + order.calculatePrice());

    }
}
