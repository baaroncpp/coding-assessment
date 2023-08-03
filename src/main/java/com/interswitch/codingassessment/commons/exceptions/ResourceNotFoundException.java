package com.interswitch.codingassessment.commons.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
@Slf4j
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message, Object ... messageConstants){
        super(String.format(message, messageConstants));
        log.error(String.format(message, messageConstants));
    }

    public ResourceNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
