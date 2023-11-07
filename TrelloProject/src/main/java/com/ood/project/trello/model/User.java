package com.ood.project.trello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.util.List;

@Document("user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @MongoId
    private String userId;
    private String userName;
    private String userRole;
}
