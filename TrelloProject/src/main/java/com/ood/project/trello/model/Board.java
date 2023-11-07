package com.ood.project.trello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("Board")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {
    @MongoId
    private String boardId;
    private String boardName;
    @DBRef
    private String userId;
}
