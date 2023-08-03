package com.interswitch.codingassessment.core.models.dto;

import com.interswitch.codingassessment.commons.utils.Validate;

import static com.interswitch.codingassessment.commons.utils.ConstantMessage.*;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 8/1/23
 **/
public record RoleRequestDto(
        String roleName,
        String codeName,
        String roleDescription
) {
    public void validate(){
        Validate.notEmpty(roleName, NULL_ROLE_NAME);
        Validate.notEmpty(codeName, NULL_CODE_NAME);
    }
}
