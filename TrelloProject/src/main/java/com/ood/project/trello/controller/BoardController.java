package com.ood.project.trello.controller;

import com.ood.project.trello.apimodel.responsemodel.AppResponse;
import com.ood.project.trello.model.Board;
import com.ood.project.trello.model.User;
import com.ood.project.trello.service.BoardService;
import com.ood.project.trello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private UserService userBoardService;
    @GetMapping("/getAll")
    public ResponseEntity<AppResponse> getAllBoards() {
        List<Board> allBoards = new ArrayList<>();
        allBoards = boardService.getAllBoards();
        return ResponseEntity.ok(new AppResponse("Boards found successfully", allBoards,HttpStatus.CREATED));

    }

    @GetMapping("/get/{boardID}")
//    TODO check how to set input parameter,(boolean) to list the tasks associated with the boards
    public ResponseEntity<AppResponse> getBoardsByBoardID(@PathVariable String boardID) {
        Board boardDetails=boardService.getBoardDeatils(boardID);
        return ResponseEntity.ok(new AppResponse("Board Created successfully",boardDetails, HttpStatus.FOUND));
    }

    @PostMapping("/createBoard")
    public ResponseEntity<AppResponse> createBoards(@RequestBody Board board) {
        boardService.createBoard(board);
        return ResponseEntity.ok(new AppResponse("Board Created successfully",board, HttpStatus.CREATED));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<AppResponse> deleteBoards(@RequestBody List<Board> boardIDs) {
        for (Board board : boardIDs) {
             boardService.deleteBoardById(board.getBoardId());
        }
        return ResponseEntity.ok(new AppResponse("User found successfully", "Deleted", HttpStatus.NO_CONTENT));
        }



//TODO should modify 1 or multiple paramaters, so should take null or some value
    @PutMapping("/modify")
    public ResponseEntity<AppResponse> modifyBoards(@RequestBody List<Board> boardDetails) {
        for (Board board : boardDetails) {
            boardService.modifyBoard(board);
        }
        return ResponseEntity.ok(new AppResponse("Board Created successfully","Boards Modified", HttpStatus.CREATED));

    }

    @GetMapping("/getUser/{boardID}")
    public ResponseEntity<AppResponse> printUserDetailsByBoardId(@PathVariable String boardID){
        Board b=boardService.getBoardDeatils(boardID);
        String user=b.getUserId();
        User userDetails=userBoardService.getUserById(user);
        String userName=userDetails.getUserName();
        return ResponseEntity.ok(new AppResponse("User found successfully", userName, HttpStatus.FOUND));

    }

}
