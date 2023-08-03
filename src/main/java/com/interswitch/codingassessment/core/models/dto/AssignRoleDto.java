package com.interswitch.codingassessment.core.models.dto;

import com.interswitch.codingassessment.commons.exceptions.model.ExceptionType;
import com.interswitch.codingassessment.commons.utils.Validate;

import static com.interswitch.codingassessment.commons.utils.ConstantMessage.*;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 8/1/23
 **/
public record AssignRoleDto(
        Long roleId,
        Long userId
) {
    public void validate(){
        Validate.notNull(roleId, ExceptionType.BAD_REQUEST, NULL_ROLE_ID);
        Validate.notNull(userId, ExceptionType.BAD_REQUEST, NULL_USER_ID);
    }
}
