package com.interswitch.codingassessment.commons.exceptions;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
public class IllegalArgumentException extends RuntimeException {
    public IllegalArgumentException(String message, Object... messageConstants) {
        super(String.format(message, messageConstants));
    }

    public IllegalArgumentException(String message) {
        super(message);
    }
}
