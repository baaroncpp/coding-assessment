package com.interswitch.codingassessment.commons.exceptions.model;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
public record ExceptionPayLoad(String uri, String message, HttpStatus httpStatus, ZonedDateTime timeStamp) {
}
