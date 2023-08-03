package com.interswitch.codingassessment.core.models.dto;

import com.interswitch.codingassessment.commons.exceptions.model.ExceptionType;
import com.interswitch.codingassessment.commons.utils.Validate;

import static com.interswitch.codingassessment.commons.utils.ConstantMessage.*;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 8/1/23
 **/
public record AssignInstitutionDto(
        Long userId,
        Long institutionId
) {
    public void validate(){
        Validate.notNull(userId, ExceptionType.BAD_REQUEST, NULL_USER_ID);
        Validate.notNull(institutionId, ExceptionType.BAD_REQUEST, NULL_INSTITUTION_ID);
    }
}
