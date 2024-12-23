package kr.ac.dankook.NoSQLProject.exception;

import lombok.Getter;

@Getter
public class BadRequestSaveEntityException extends RuntimeException {
    private final ErrorCode errorCode;
    public BadRequestSaveEntityException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
