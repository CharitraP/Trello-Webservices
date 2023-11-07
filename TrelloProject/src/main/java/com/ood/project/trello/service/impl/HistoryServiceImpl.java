package com.ood.project.trello.service.impl;

import com.ood.project.trello.model.History;
import com.ood.project.trello.model.Task;
import com.ood.project.trello.repository.HistoryRepository;
import com.ood.project.trello.service.HistoryService;
import com.ood.project.trello.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;
    private TaskService taskService;

    public List<History> getAllHistory(){
        return historyRepository.findAll();
    }

    @Override
    public List<History> getHistory(String taskID) {
        List<History> fecthedHistory = new ArrayList<>();
        fecthedHistory = historyRepository.findByTaskID(taskID);

        Collections.sort(fecthedHistory, (a, b) -> a.getTimeCreated().compareTo(b.getTimeCreated()));
        Collections.reverse(fecthedHistory);

        if(fecthedHistory.size() != 0){
            return fecthedHistory;
        }

        return null;
    }

    @Override
    public History getCreationTime(String taskID) {
        List<History> fecthedHistory = new ArrayList<>();
        fecthedHistory = historyRepository.findByTaskID(taskID);

        Collections.sort(fecthedHistory, (a, b) -> a.getTimeCreated().compareTo(b.getTimeCreated()));
        //Collections.reverse(fecthedHistory);

        if(fecthedHistory.size() > 0){
            return fecthedHistory.get(0);
        }

        return null;
    }

    @Override
    public History createHistory(Task task) {
        Timestamp timeCreated = new Timestamp(new Date().getTime());

        if(task == null)
            return null;
        System.out.println(task.getTaskID());
        History dbHistory = new History();
        dbHistory.setTaskID(task.getTaskID());
        dbHistory.setTaskStatus(task.getStatus());
        dbHistory.setTimeCreated(timeCreated);

        return historyRepository.save(dbHistory);
    }
}
