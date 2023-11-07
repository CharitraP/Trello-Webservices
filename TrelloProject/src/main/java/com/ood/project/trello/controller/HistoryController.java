package com.ood.project.trello.controller;

import com.ood.project.trello.apimodel.responsemodel.AppResponse;
import com.ood.project.trello.model.History;
import com.ood.project.trello.model.Status;
import com.ood.project.trello.model.Task;
import com.ood.project.trello.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("/all")
    public ResponseEntity<AppResponse> getAllHistory(){
        List<History> allHistory = new ArrayList<>();

        allHistory = historyService.getAllHistory();

        if(allHistory == null)
            return ResponseEntity.ok(new AppResponse("No History found", allHistory, HttpStatus.NO_CONTENT));

        return ResponseEntity.ok(new AppResponse("History found successfully", allHistory, HttpStatus.FOUND));
    }

    @GetMapping("/get/{taskID}")
    public ResponseEntity<AppResponse> getHistory(@PathVariable String taskID){
        List<History> getHistory = new ArrayList<>();

        getHistory = historyService.getHistory(taskID);

        if(getHistory != null)
            return ResponseEntity.ok(new AppResponse("History exists for taskID: " + taskID, getHistory, HttpStatus.FOUND));

        return ResponseEntity.ok(new AppResponse("History does NOT exists for taskID: " + taskID, getHistory, HttpStatus.NO_CONTENT));
    }

    @GetMapping("/getCreationTime/{taskID}")
    public ResponseEntity<AppResponse> getCreationTime(@PathVariable String taskID){
        History firstOccurrence = historyService.getCreationTime(taskID);

        if(firstOccurrence != null)
            return ResponseEntity.ok(new AppResponse("First Occurrence found for taskID: " + taskID, firstOccurrence, HttpStatus.FOUND));

        return ResponseEntity.ok(new AppResponse("First Occurrence NOT found for taskID: " + taskID, firstOccurrence, HttpStatus.FOUND));
    }
}

