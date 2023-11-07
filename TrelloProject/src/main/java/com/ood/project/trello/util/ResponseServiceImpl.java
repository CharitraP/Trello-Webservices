package com.ood.project.trello.util;

import com.ood.project.trello.model.Board;
import com.ood.project.trello.model.Task;
import com.ood.project.trello.model.User;
import com.ood.project.trello.service.BoardService;
import com.ood.project.trello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponseServiceImpl {
    @Autowired
    UserService userService;

    @Autowired
    BoardService boardService;
    
    public Object getResponseObject(Object o){
        if(o instanceof Task) {
            ((Task) o).setAssignedTo(userService.getUserById(((Task) o).getAssignedTo()).getUserName());
            ((Task) o).setBoard(boardService.getBoardDeatils(((Task) o).getBoard()).getBoardName());
            return o;
        }
        return null;
    }
}
