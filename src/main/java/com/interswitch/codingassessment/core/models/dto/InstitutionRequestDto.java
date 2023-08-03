package com.interswitch.codingassessment.core.models.dto;

import com.interswitch.codingassessment.commons.utils.Validate;
import static com.interswitch.codingassessment.commons.utils.ConstantMessage.*;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/31/23
 **/
public record InstitutionRequestDto(
        String institutionCode,
        String institutionName
) {
    public void validate(){
        Validate.notEmpty(institutionCode, NULL_INST_CODE);
        Validate.notEmpty(institutionName, NULL_INST_NAME);
    }
}
