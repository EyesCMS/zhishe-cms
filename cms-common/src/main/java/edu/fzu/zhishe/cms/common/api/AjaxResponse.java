package edu.fzu.zhishe.cms.common.api;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.FieldError;

/**
 * @author liang on 4/11/2020.
 * @version 1.0
 */
public class AjaxResponse {

//    {
//        "message": "Validation Failed",
//        "errors": [
//        {
//            "resource": "Issue",
//            "field": "title",
//            "code": "missing_field"
//        }
//  ]
//    }

    private String message;
    private List<Error> errors = new ArrayList<>();

    public AjaxResponse() {
    }

    public AjaxResponse(String message, List<Error> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public AjaxResponse message(String message) {
        AjaxResponse response = new AjaxResponse();
        response.setMessage(message);
        return response;
    }

    public AjaxResponse errors(Error error) {
        this.setMessage("Validation Failed");
        this.getErrors().add(error);
        return this;
    }

//    public AjaxResponse message(String message) {
//        this.message = message;
//        return this;
//    }
//
//    public AjaxResponse error(Error error) {
//        this.errors.add(error);
//        return this;
//    }
}
