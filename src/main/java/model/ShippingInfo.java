package model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shippings")
@Data
public class ShippingInfo {

    @Id
    private String id;
    private String orderId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
    private String city;
    private String postalCode;
    private String country;
    private ShippingStatus status;
}
