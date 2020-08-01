package io.github.edmaputra.ed.edbase.util;

import io.github.edmaputra.ed.edbase.model.error.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class ResponseUtil {

  public static ResponseEntity<ErrorModel> createExceptionResponse(Exception ex, HttpStatus status) {
    ErrorModel errorModel = new ErrorModel();
    errorModel.addErrors("error", ex.getLocalizedMessage());
    errorModel.setStatus(status);
    return new ResponseEntity<>(errorModel, errorModel.getStatus());
  }

  public static ResponseEntity<ErrorModel> createExceptionResponse(Map<String, List<String>> error, HttpStatus status) {
    ErrorModel errorModel = new ErrorModel(status, error);
    return new ResponseEntity<>(errorModel, errorModel.getStatus());
  }

}
