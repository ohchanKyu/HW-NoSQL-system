package kr.ac.dankook.NoSQLProject.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {


    BAD_REQUEST_NEARBY_PLACE(HttpStatus.BAD_REQUEST, "원하시는 주소를 반드시 입력해주세요."),
    BAD_REQUEST_OF_SAVE_ENTITY(HttpStatus.BAD_REQUEST, "장소의 이름과 주소를 반드시 입력해주세요."),
    API_JSON_PARSING_ERROR(HttpStatus.NOT_ACCEPTABLE,"주소를 좌표로 변환 중 에러가 발생하였습니다. 주소를 정확히 입력해주세요");
    
    private final HttpStatus httpStatus;
    private final String message;
}