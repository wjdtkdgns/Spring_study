package com.example.CommonErrorResponse.config;

import com.example.CommonErrorResponse.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Getter
@Builder
@Slf4j
public class ErrorResponse extends Throwable{
    private final boolean success = false;
    private int status;
    private String message;
    private List<FieldError> errors;
    private String code;

    public static ErrorResponse of(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .message(errorCode.getMessage())
                .status(errorCode.getHttpStatus().value())
                .errors(Arrays.asList())
                .code(errorCode.getHttpStatus().name())
                .build();
    }

    public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult) {
        log.info(bindingResult.getTarget().toString());

        return ErrorResponse.builder()
                .message(errorCode.getMessage())
                .status(errorCode.getHttpStatus().value())
                .code(errorCode.getHttpStatus().name()) //
                .build();
    }

    // 나중에 구현 예정, dto 검증 용
    @Getter
    @NoArgsConstructor
    public static class FieldError {
        private String field;
        private String value;
        private String reason;
    }

}
