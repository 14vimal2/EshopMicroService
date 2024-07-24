package com.eshop.userservice.controllers.advisor;


import com.eshop.userservice.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(ProductNotFoundException ex) {
//        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(ex.getMessage());
//        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
