package com.example.demo.handler;

import com.example.demo.exception.ErrorResult;
import com.example.demo.exception.trainee.TraineeNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResult> constraintException(ConstraintViolationException ex) {
        ErrorResult errorResult = ErrorResult
                .builder().errorMessage("对象校验错误").details(new HashMap<>()).build();
        ex.getConstraintViolations()
                .stream().forEach(s -> errorResult.getDetails()
                .put(s.getInvalidValue().toString(), s.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResult);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handle(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorResult errorResult = ErrorResult.builder()
                .errorMessage(message).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(TraineeNotExistException.class)
    public ResponseEntity userExistException(TraineeNotExistException traineeNotExistException) {
        ErrorResult errorResult = new ErrorResult(traineeNotExistException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResult);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResult> requestParameterNotMatch(MethodArgumentTypeMismatchException ex) {
        String parameterName = ex.getParameter().getParameterName();
        String message = parameterName + "类型不匹配";
        ErrorResult errorResult = new ErrorResult(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
