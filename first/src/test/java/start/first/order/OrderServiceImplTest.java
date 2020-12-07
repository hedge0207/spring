package start.first.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import start.first.discount.FixDiscountPolicy;
import start.first.discount.RateDiscountPolicy;
import start.first.member.Grade;
import start.first.member.Member;
import start.first.member.MemoryMemberRepository;

public class OrderServiceImplTest {

    @Test
    void createOrder(){
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memoryMemberRepository.save(new Member(1L,"userA", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        Order order = orderService.createOrder(1L,"itemA",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
