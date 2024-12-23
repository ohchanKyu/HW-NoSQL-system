package kr.ac.dankook.NoSQLProject.exception;

import lombok.Getter;

@Getter
public class BadRequestNearbyPlaceException extends RuntimeException {
    private final ErrorCode errorCode;
    public BadRequestNearbyPlaceException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}