package io.github.edmaputra.ed.edbase.controller.advice;

import io.github.edmaputra.ed.edbase.exception.CrudOperationException;
import io.github.edmaputra.ed.edbase.exception.DataEmptyException;
import io.github.edmaputra.ed.edbase.exception.DataNotFoundException;
import io.github.edmaputra.ed.edbase.model.error.ErrorModel;
import io.github.edmaputra.ed.edbase.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public abstract class BaseControllerExceptionHandler {

  @ExceptionHandler({
      CrudOperationException.class,
      MethodArgumentTypeMismatchException.class
  })
  protected ResponseEntity<ErrorModel> onCrudOperationException(
      Exception ex) {
    return ResponseUtil.createExceptionResponse(ex, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({
      DataEmptyException.class,
      DataNotFoundException.class
  })
  protected ResponseEntity<ErrorModel> onDataEmptyOrNotFoundException(Exception ex) {
    return ResponseUtil.createExceptionResponse(ex, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<ErrorModel> onConstraintValidationException(ConstraintViolationException ex) {
    ErrorModel errorModel = new ErrorModel();
    for (ConstraintViolation violation : ex.getConstraintViolations()) {
      errorModel.addErrors(violation.getPropertyPath().toString(), violation.getMessage());
    }
    errorModel.setStatus(HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(errorModel, errorModel.getStatus());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ErrorModel> onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    ErrorModel errorModel = new ErrorModel();

    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
      errorModel.addErrors(fieldError.getField(), fieldError.getDefaultMessage());
    }
    errorModel.setStatus(HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(errorModel, errorModel.getStatus());
  }

}
