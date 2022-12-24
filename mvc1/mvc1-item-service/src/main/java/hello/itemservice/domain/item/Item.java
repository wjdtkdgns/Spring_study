package hello.itemservice.domain.item;

import lombok.Data;

@Data // 예측 못하게 동작할 수도 -> 그냥 Getter, Setter 쓰는거 추천
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
