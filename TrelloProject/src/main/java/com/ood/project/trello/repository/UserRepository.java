package com.ood.project.trello.repository;
import com.ood.project.trello.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{userName: '?0'}")
    User findByUserName(String userName);


    @Query("{userId: '?0'}")

    User findByUserId(String userID);
}
