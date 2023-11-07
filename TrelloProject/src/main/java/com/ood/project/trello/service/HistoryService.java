package com.ood.project.trello.service;

import com.ood.project.trello.model.History;
import com.ood.project.trello.model.Task;

import java.util.List;


public interface HistoryService {
    public List<History> getAllHistory();

    public List<History> getHistory(String taskID);

    public History getCreationTime(String taskID);

    public History createHistory(Task task);

}
