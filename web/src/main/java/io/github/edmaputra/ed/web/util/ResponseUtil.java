package io.github.edmaputra.ed.web.util;

import io.github.edmaputra.ed.web.controller.advice.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

  public static ResponseEntity<Object> createExceptionResponse(Exception ex, HttpStatus status) {
    String message = ex.getMessage();
    ApiError apiError = new ApiError(status, ex.getLocalizedMessage(), message);
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

}
