package com.ood.project.trello.service;


import com.ood.project.trello.model.Task;

import java.util.*;

public interface TaskService {

    Task createTask(Task task);

    void deleteTasks(List<String> taskId);

    Boolean modifyTasks(Task task);

    List<Task> getAllTasks();

    Task getTask(String taskId);

    List<Task> getTasksByBoardID(String boardId);

}
