package kr.co.polycube.backendtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //2.1 TODO: API 호출 시, 잘못된 요청이 들어오면 HTTP 400상태 {"reason":"실제사유"} 반환
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<Map<String, String>> badRequest(IllegalArgumentException illegalArgumentException){
        Map<String, String> response = new HashMap<>();
        response.put("reason", "잘못된 요청입니다: "+ illegalArgumentException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    // 2.1 TODO: 존재하지 않는 API 호출 시, HTTP 404상태 {"reason": "실제사유"} 반환
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, String>> notFound(NoHandlerFoundException noHandlerFoundException){
        Map<String, String> response = new HashMap<>();
        response.put("reason", "존재하지 않는 API입니다: "+ noHandlerFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
