package com.ood.project.trello.repository;

import com.ood.project.trello.model.History;
import com.ood.project.trello.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends MongoRepository<History, String> {

    @Query("{taskID:  ?0}")
    List<History> findByTaskID(String taskID);
}
