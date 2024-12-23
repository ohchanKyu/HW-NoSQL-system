package kr.ac.dankook.NoSQLProject.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiJsonParsingException.class)
    protected ResponseEntity<ErrorResponse> handleApiJsonParsingException(ApiJsonParsingException ex){
        ErrorCode errorCode = ex.getErrorCode();
        return handleExceptionInternal(errorCode);
    }
    @ExceptionHandler(BadRequestSaveEntityException.class)
    protected ResponseEntity<ErrorResponse> handleBadRequestSaveEntityException(BadRequestSaveEntityException ex){
        ErrorCode errorCode = ex.getErrorCode();
        return handleExceptionInternal(errorCode);
    }
    @ExceptionHandler(BadRequestNearbyPlaceException.class)
    protected ResponseEntity<ErrorResponse> handleBadRequestNearbyPlaceException(BadRequestNearbyPlaceException ex){
        ErrorCode errorCode = ex.getErrorCode();
        return handleExceptionInternal(errorCode);
    }
    private ResponseEntity<ErrorResponse> handleExceptionInternal(ErrorCode errorCode){
        return ResponseEntity
                .status(errorCode.getHttpStatus().value())
                .body(new ErrorResponse(errorCode));
    }
}
