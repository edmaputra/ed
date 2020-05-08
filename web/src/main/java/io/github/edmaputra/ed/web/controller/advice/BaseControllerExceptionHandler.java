package io.github.edmaputra.ed.web.controller.advice;

import io.github.edmaputra.ed.core.exception.CrudOperationException;
import io.github.edmaputra.ed.core.exception.DataEmptyException;
import io.github.edmaputra.ed.core.exception.DataNotFoundException;
import io.github.edmaputra.ed.web.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseControllerExceptionHandler {

  @ExceptionHandler({
      CrudOperationException.class,
      MethodArgumentTypeMismatchException.class
  })
  protected ResponseEntity<Object> onCrudOperationException(
      Exception ex) {
    return ResponseUtil.createExceptionResponse(ex, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({
      DataEmptyException.class,
      DataNotFoundException.class
  })
  protected ResponseEntity<Object> onDataEmptyOrNotFoundException(Exception ex) {
    return ResponseUtil.createExceptionResponse(ex, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<Object> onConstraintValidationException(ConstraintViolationException ex) {
    String message = ex.getMessage();
    List<String> errors = new ArrayList<>();
    for (ConstraintViolation violation : ex.getConstraintViolations()) {
      errors.add(violation.getPropertyPath().toString() + "; " + violation.getMessage());
      //      errors.add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
    }
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, errors);
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<Object> onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    String message = ex.getMessage();
    List<String> errors = new ArrayList<>();
    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
      errors.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
//      errors.add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
    }
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, errors);
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  static class Violation {
    private String field;
    private String messages;

    public Violation(String field, String messages) {
      this.field = field;
      this.messages = messages;
    }

    public String getField() {
      return field;
    }

    public String getMessages() {
      return messages;
    }
  }

}
