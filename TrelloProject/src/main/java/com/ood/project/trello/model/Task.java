package com.ood.project.trello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document("Task")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {

    @MongoId
    private String taskID;
    @NonNull
    private Status status;
    private String description;
    private List<String> comments;
    private String assignedTo;
    private String board;
}







