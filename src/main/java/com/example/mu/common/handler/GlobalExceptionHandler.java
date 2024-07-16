package com.example.mu.common.handler;

import com.example.mu.common.errorCd.ErrorCd;
import com.example.mu.common.contants.ConstantsResponse;
import com.example.mu.common.handler.exception.NotFoundException;
import com.example.mu.common.vo.ReturnVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> NotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(new ReturnVO(ErrorCd.NOT_EXIST_DATA, ex.getMessage()), ErrorCd.NOT_EXIST_DATA.getStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ReturnVO> IllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(new ReturnVO(ErrorCd.NOT_EXIST_DATA, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> Exception(Exception ex, WebRequest request) {
        return ResponseEntity.internalServerError().body(ConstantsResponse.RES_SYSTEM_ERROR);
    }
}