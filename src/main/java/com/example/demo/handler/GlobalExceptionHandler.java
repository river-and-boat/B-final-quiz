package com.example.demo.handler;

import com.example.demo.exception.ErrorResult;
import com.example.demo.exception.group.GroupNameHasExistException;
import com.example.demo.exception.group.GroupNotExistException;
import com.example.demo.exception.group.GroupingException;
import com.example.demo.exception.trainee.TraineeNotExistException;
import com.example.demo.exception.trainer.TrainerNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        ErrorResult errorResult = ErrorResult.builder()
                .errorMessage("Invalid params").build();
        Map<String, String> errorMap = new HashMap<>();
        fieldErrors.stream().forEach(f -> {
            errorMap.put(f.getField(), f.getDefaultMessage());
        });
        errorResult.setDetails(errorMap);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(TraineeNotExistException.class)
    public ResponseEntity traineeExistException(TraineeNotExistException traineeNotExistException) {
        ErrorResult errorResult = new ErrorResult(traineeNotExistException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResult);
    }

    @ExceptionHandler(TrainerNotExistException.class)
    public ResponseEntity trainerExistException(TrainerNotExistException trainerNotExistException) {
        ErrorResult errorResult = new ErrorResult(trainerNotExistException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResult);
    }

    @ExceptionHandler(GroupNameHasExistException.class)
    public ResponseEntity groupNameHasExist(GroupNameHasExistException groupNameHasExistException) {
        ErrorResult errorResult = new ErrorResult(groupNameHasExistException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResult);
    }

    @ExceptionHandler(GroupNotExistException.class)
    public ResponseEntity groupNotExist(GroupNotExistException groupNotExistException) {
        ErrorResult errorResult = new ErrorResult(groupNotExistException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResult);
    }

    @ExceptionHandler(GroupingException.class)
    public ResponseEntity groupingException(GroupingException groupingException) {
        ErrorResult errorResult = new ErrorResult(groupingException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResult);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResult> requestParameterNotMatch(MethodArgumentTypeMismatchException ex) {
        String parameterName = ex.getParameter().getParameterName();
        String message = parameterName + "类型不匹配";
        ErrorResult errorResult = new ErrorResult(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResult> requestUrlParamLose(MissingServletRequestParameterException ex) {
        String parameterName = ex.getParameterName();
        String message = "URL参数" + parameterName + "缺失";
        ErrorResult errorResult = new ErrorResult(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
