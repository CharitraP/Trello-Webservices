package com.ood.project.trello.repository;

import com.ood.project.trello.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.*;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String> {

    @Query("{taskID: '?0'}")
    Optional<Task> findByTaskId(String taskId);

    @Query("{boardId: '?0'}")
    Optional<List<Task>> findAllByBoardId(String boardId);
}
