package com.ood.project.trello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document("History")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class History {
    @MongoId
    private String historyID;
    private String taskID;
    private Status taskStatus;
    private Date timeCreated;
}
