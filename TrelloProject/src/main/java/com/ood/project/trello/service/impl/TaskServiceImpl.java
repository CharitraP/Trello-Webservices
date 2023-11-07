package com.ood.project.trello.service.impl;

import com.ood.project.trello.model.Task;
import com.ood.project.trello.repository.TaskRepository;
import com.ood.project.trello.service.HistoryService;
import com.ood.project.trello.service.TaskService;
import com.ood.project.trello.util.ResponseServiceImpl;
import com.ood.project.trello.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private HistoryService historyService;

    public Task createTask(Task task){
        if(validationService.isValidBoard(task.getBoard()) && validationService.isValidUser(task.getAssignedTo())){
            Task taskOne = taskRepository.save(task);
            historyService.createHistory(taskOne);
            return taskOne;

        }
        return null;
    }
    @Override
    public void deleteTasks(List<String> taskIds){
        for(String t: taskIds){
            if(t != null){
                Task task = getTask(t);
                taskRepository.delete(task);
            }
        }

    }
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }


    public Boolean modifyTasks(Task taskUpdates){
        Task taskToBeUpdated = getTask(taskUpdates.getTaskID());
        if (taskToBeUpdated != null && validationService.isValidUser(taskUpdates.getAssignedTo())){
            // Update status
            if (taskUpdates.getStatus() != null) {
                if(taskToBeUpdated.getStatus() != taskUpdates.getStatus()){
                    System.out.println(taskUpdates.getTaskID());
                    historyService.createHistory(taskUpdates);
                }
                taskToBeUpdated.setStatus(taskUpdates.getStatus());
            }
            // Update description if provided and not empty
            if (taskUpdates.getDescription() != null && !taskUpdates.getDescription().isEmpty()) {
                taskToBeUpdated.setDescription(taskUpdates.getDescription());
            }
            // Append comments
            if (taskUpdates.getComments() != null && !taskUpdates.getComments().isEmpty()) {
                List<String> existingComments = taskToBeUpdated.getComments();
                existingComments.addAll(taskUpdates.getComments());
                taskToBeUpdated.setComments(existingComments);
            }
            // Update assignedTo if provided and not empty
            if (taskUpdates.getAssignedTo() != null && !taskUpdates.getAssignedTo().isEmpty()) {
                taskToBeUpdated.setAssignedTo(taskUpdates.getAssignedTo());
            }
            taskToBeUpdated.setBoard(taskToBeUpdated.getBoard());
            // Save the updated task
            taskRepository.save(taskToBeUpdated);
            return true;
        }
        return false;
    }
    public Task getTask(String taskId)  {
        return taskRepository.findByTaskId(taskId).orElse(null);
    }
     public List<Task> getTasksByBoardID(String boardId){
        return taskRepository.findAllByBoardId(boardId).orElse(null);
     }
}
