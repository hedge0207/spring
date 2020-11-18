package start.first.order;

public interface OrderService {
    
    //주문 등록
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
