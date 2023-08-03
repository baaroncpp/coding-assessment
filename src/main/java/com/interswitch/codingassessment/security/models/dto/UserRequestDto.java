package com.interswitch.codingassessment.security.models.dto;


import com.interswitch.codingassessment.commons.exceptions.model.ExceptionType;
import com.interswitch.codingassessment.commons.utils.Validate;

import static com.interswitch.codingassessment.commons.utils.ConstantMessage.*;
import static com.interswitch.codingassessment.commons.utils.Validate.*;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/31/23
 **/
public record UserRequestDto(
        String username,
        String password,
        String lastName,
        String firstName,
        String emailAddress,
        String userType,
        String corporateType,
        Long roleId
) {
    public void validate(){
        Validate.notEmpty(username, NULL_USERNAME);
        Validate.notEmpty(password, NULL_PASSWORD);
        Validate.notEmpty(lastName, NULL_LAST_NAME);
        Validate.notEmpty(firstName, NULL_FIRST_NAME);
        Validate.notEmpty(emailAddress, NULL_EMAIL);
        Validate.notEmpty(userType, NULL_USER_TYPE);
        Validate.notEmpty(corporateType, NULL_CORPORATE_TYPE);
        stringOfEmail(emailAddress, INVALID_MAIL, emailAddress);
        Validate.isTrue(isUserType(userType), ExceptionType.BAD_REQUEST, INVALID_USER_TYPE);
        Validate.isTrue(isCorporateType(corporateType), ExceptionType.BAD_REQUEST, INVALID_CORPORATE_TYPE);
        Validate.notNull(roleId, ExceptionType.BAD_REQUEST, NULL_ROLE_ID);
    }
}
