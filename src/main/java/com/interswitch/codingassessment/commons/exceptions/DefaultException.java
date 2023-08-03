package com.interswitch.codingassessment.commons.exceptions;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
public class DefaultException  extends RuntimeException{
    public DefaultException(String message, Object ... messageConstants){
        super(String.format(message, messageConstants));
    }

    public DefaultException(String message) {
        super(message);
    }
}
