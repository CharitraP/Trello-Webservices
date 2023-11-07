package com.ood.project.trello.service.impl;

import com.ood.project.trello.model.Board;
import com.ood.project.trello.repository.BoardRepository;
import com.ood.project.trello.service.BoardService;
//import com.ood.project.trello.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //@Autowired
    //private ValidationService validationService;

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }
    @Override
    public void createBoard(Board board) {
//        if(validationService.isValidUser(board.getUserId())) {
//            boardRepository.save(board);
//        }
        boardRepository.save(board);
    }
    @Override
    public Board getBoardDeatils(String boardID) {
        return boardRepository.findByBoardId(boardID);
    }

    @Override
    public void deleteBoardById(String boardId) {
        boardRepository.deleteByBoardId(boardId);
    }

    @Override
    public void modifyBoard(Board board) {
        boardRepository.save(board);
    }

}
