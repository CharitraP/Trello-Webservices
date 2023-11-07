package com.ood.project.trello.apimodel.responsemodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppResponse<T>{
    private String message;
    private T payload;
    private HttpStatus status;
}
