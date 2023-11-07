package com.ood.project.trello.repository;

import com.ood.project.trello.model.Board;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.Query;

@EnableMongoRepositories
public interface BoardRepository extends MongoRepository<Board, String> {
    Board findByBoardId(String boardId);
     void deleteByBoardId(String boardId);
}
