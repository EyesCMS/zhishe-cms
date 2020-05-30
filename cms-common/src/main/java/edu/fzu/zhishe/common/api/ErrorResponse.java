package edu.fzu.zhishe.common.api;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liang on 4/11/2020.
 * @version 1.0
 */
public class ErrorResponse {

//    {
//        "message": "Validation Failed",
//    }

    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ErrorResponse message(String message) {
        ErrorResponse response = new ErrorResponse();
        response.message = message;
        return response;
    }
}
