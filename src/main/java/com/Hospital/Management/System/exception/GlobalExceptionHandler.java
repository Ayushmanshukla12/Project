//package com.Hospital.Management.System.exception;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFound(ResourceNotFoundException exception,
//                                                               WebRequest webRequest) {
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
//                webRequest.getDescription(false));
//
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(BlogAPIException.class)
//    public ResponseEntity<ErrorDetails> handleBlogAPIException(BlogAPIException exception,
//                                                               WebRequest webRequest) {
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
//                webRequest.getDescription(false));
//
//        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//    }
//
//    // handle global exceptions
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDetails> handleBlogAPIException(Exception exception,
//                                                               WebRequest webRequest) {
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
//                webRequest.getDescription(false));
//
//        return new ResponseEntity<>
//                (errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    // First Approach
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        Map<String,String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach(objectError -> {
//            String fieldName = ((FieldError)objectError).getField();
//            String message = objectError.getDefaultMessage();
//            errors.put(fieldName,message);
//        });
//
//        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
//    }
