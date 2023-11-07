package com.ood.project.trello.util;

import com.ood.project.trello.service.BoardService;
import com.ood.project.trello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    @Autowired
    private UserService userService;

    @Autowired
    private BoardService boardService;

    public boolean isValidUser(String userId) {
        return userService.getUserById(userId) != null;
    }

    public boolean isValidBoard(String boardId) {
        return boardService.getBoardDeatils(boardId) != null;
    }
}

