package com.example.CommonErrorResponse;

import com.example.CommonErrorResponse.config.CommonResponse;
import com.example.CommonErrorResponse.exception.BadRequestException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.CommonErrorResponse.ErrorCode.TEST;

@Slf4j
@RestController
public class Controller {

    @GetMapping("/")
    public CommonResponse<String> aa(@RequestBody Test test) {
        log.info("{}", ErrorCode.TEST.getCode());
        if(test.getNum() == 1L)
            throw new BadRequestException(ErrorCode.TEST);
        return new CommonResponse<String>("1",Boolean.TRUE,"1","1");
    }

    @Data
    static class Test {
        private Long num;
    }
}
