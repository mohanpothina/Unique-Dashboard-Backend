package model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admins")
@Data
public class Admin {
    @Id
    private String id;
    private String username;
    private String password;

}