package start.first.singleton;

public class StatefulService {

    private int price; //상태를 유지하는 필드

    public void order(String name, int price){
        System.out.println("name = " + name + " price = " + price);

        //여기가 문제가 된다.
        this.price=price;
    }

    public int getPrice(){
        return price;
    }
}
