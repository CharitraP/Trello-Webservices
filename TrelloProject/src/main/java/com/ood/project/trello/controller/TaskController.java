package com.ood.project.trello.controller;

import com.ood.project.trello.apimodel.responsemodel.AppResponse;
import com.ood.project.trello.model.Status;
import com.ood.project.trello.model.Task;
import com.ood.project.trello.service.TaskService;
import com.ood.project.trello.util.ResponseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;
import java.util.List;


@RestController
//TODO: @RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ResponseServiceImpl responseService;



    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok(new AppResponse("List of Tasks", taskService.getAllTasks(), HttpStatus.OK));
    }

    @GetMapping("/get/{taskID}")
    public ResponseEntity<?> getTasksByTaskID(@PathVariable String taskID) {
        AppResponse appResponse = new AppResponse("Task not found", null, HttpStatus.NOT_FOUND);
        Task task = taskService.getTask(taskID);
        if(task != null){
            appResponse.setStatus(HttpStatus.OK);
            appResponse.setMessage("Task found successfully");
            appResponse.setPayload(responseService.getResponseObject(task));
        }
        return ResponseEntity.status(appResponse.getStatus()).body(appResponse);
    }

    @GetMapping("/getAll/{boardID}")
    public ResponseEntity<?> getTasksByBoardID(@PathVariable String boardID){
        AppResponse appResponse = new AppResponse("Tasks with this BoardId does not exist", null, HttpStatus.NOT_FOUND);
        List<Task> tasks = taskService.getTasksByBoardID(boardID);
        if(!(tasks == null || tasks.isEmpty())){
            appResponse.setStatus(HttpStatus.OK);
            appResponse.setMessage("Tasks found successfully");
            appResponse.setPayload(tasks);
        }
        return ResponseEntity.status(appResponse.getStatus()).body(appResponse);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody Task task) {
         AppResponse appResponse = new AppResponse("Task not created, Please check if you have specified the boardId and assignedTo fields correctly", null, HttpStatus.BAD_REQUEST);
        //BoardId validation
        if(task.getBoard()==null || task.getBoard().isEmpty()){
            return ResponseEntity.status(appResponse.getStatus()).body(appResponse);
        }
        Task createdTask = taskService.createTask(task);
        if(createdTask != null){
            appResponse.setStatus(HttpStatus.CREATED);
            appResponse.setMessage("Task created successfully");
            appResponse.setPayload(responseService.getResponseObject(createdTask));
        }
        return ResponseEntity.status(appResponse.getStatus()).body(appResponse);
    }


    @DeleteMapping("/remove")
    public ResponseEntity<?> deleteTasks(@RequestBody List<String> taskIDs) {
        taskService.deleteTasks(taskIDs);
        return ResponseEntity.ok().body(new AppResponse("Task deleted successfully", null , HttpStatus.OK));
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modifyTasks(@RequestBody Task task) {
        Boolean value = taskService.modifyTasks(task);
        AppResponse appResponse = new AppResponse("Couldn't modify the task. Make sure that the taskID or assignedTo given is correct", null, HttpStatus.BAD_REQUEST);
        if (value == true){
            appResponse.setStatus(HttpStatus.OK);
            appResponse.setMessage("Task modified successfully");
        }
        return ResponseEntity.ok().body(appResponse);
    }
}
