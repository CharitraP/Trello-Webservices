package com.ood.project.trello.service;

import com.ood.project.trello.model.Board;
import com.ood.project.trello.model.User;

import java.util.List;

public interface BoardService {
    public List<Board> getAllBoards();

    public void createBoard(Board board);

    public Board getBoardDeatils(String boardID);

    public void deleteBoardById(String boardId);

    public void modifyBoard(Board board);

}
