package com.interswitch.codingassessment.commons.utils;

import com.interswitch.codingassessment.commons.exceptions.*;
import com.interswitch.codingassessment.commons.exceptions.IllegalArgumentException;
import com.interswitch.codingassessment.commons.exceptions.model.ExceptionType;
import com.interswitch.codingassessment.core.models.enums.CorporateType;
import com.interswitch.codingassessment.core.models.enums.UserType;
import com.interswitch.codingassessment.core.models.jpa.User;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
public class Validate {
    private Validate() {}

    public static void isTrue(boolean value, ExceptionType exceptionType, String message, Object ... params){
        if (!value && exceptionType.equals(ExceptionType.BAD_REQUEST)) {
            throw new BadRequestException(message, params);
        }

        if (!value && exceptionType.equals(ExceptionType.RESOURCE_NOT_FOUND)) {
            throw new ResourceNotFoundException(message, params);
        }

        if (!value && exceptionType.equals(ExceptionType.BAD_CREDENTIALS)) {
            throw new BadCredentialsException(message, params);
        }

    }

    public static void notNull(Object value, ExceptionType exceptionType, String message, Object ... params){

        if (value == null && exceptionType.equals(ExceptionType.BAD_REQUEST)) {
            throw new BadRequestException(message, params);
        }

        if (value == null && exceptionType.equals(ExceptionType.RESOURCE_NOT_FOUND)) {
            throw new ResourceNotFoundException(message, params);
        }

        if (value == null && exceptionType.equals(ExceptionType.BAD_CREDENTIALS)) {
            throw new BadCredentialsException(message, params);
        }

        if (value == null && exceptionType.equals(ExceptionType.ILLEGAL_ARG)) {
            throw new IllegalArgumentException(message, params);
        }

    }

    public static void notEmpty(String value, String message, Object ... params){
        if (!StringUtils.hasLength(value)) {
            throw new BadRequestException(message, params);
        }
    }

    public static void isPresent(Optional<?> value, String message, Object ... params){
        if(value.isEmpty()){
            throw new ResourceNotFoundException(String.format(message,params));
        }
    }

    public static boolean isUserType(String value){
        List<String> userTypeList = Arrays.asList(
                UserType.USER_A.name(),
                UserType.USER_B.name(),
                UserType.USER_C.name()
        );
        return userTypeList.contains(value);
    }

    public static boolean isCorporateType(String value){
        List<String> corporateTypeList = Arrays.asList(
                CorporateType.BANK.name(),
                CorporateType.OTHERS.name()
        );
        return corporateTypeList.contains(value);
    }

    public static void stringOfEmail(String value, String message, Object ... params){
        if(!Pattern.matches("^(.+)@(.+)$", value)){
            throw new IllegalArgumentException(message, params);
        }
    }
}
