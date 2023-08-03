package com.interswitch.codingassessment.commons.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
@Slf4j
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message, Object ... messageConstants){
        super(String.format(message, messageConstants));
        log.error(String.format(message, messageConstants));
    }

    public BadRequestException(String message) {
        super(message);
        log.error(message);
    }
}

