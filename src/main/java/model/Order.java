package model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
@Data
public class Order {
    @Id
    private String orderId;
    private String userId;
    private double totalAmount;

}
