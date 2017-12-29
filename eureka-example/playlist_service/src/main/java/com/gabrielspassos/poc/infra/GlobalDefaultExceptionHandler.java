package com.gabrielspassos.poc.infra;

import com.gabrielspassos.poc.exception.FailToAcessOtherApi;
import com.gabrielspassos.poc.exception.IdNotFound;
import com.gabrielspassos.poc.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = IdNotFound.class)
    public ResponseEntity<Error> idNotFound(){
        Error error = new Error("This playlist don't exist");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value = FailToAcessOtherApi.class)
    public ResponseEntity<Error> failToConnect(){
        Error error = new Error("Fail to connect other micro service");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
