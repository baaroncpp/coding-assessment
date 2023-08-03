package com.interswitch.codingassessment.core.models.dto;

import com.interswitch.codingassessment.core.models.jpa.User;
import com.interswitch.codingassessment.security.models.dto.UserResponseDto;

import java.util.Date;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 8/2/23
 **/
public record InstitutionResponseDto(
        Long id,
        Date createdOn,
        Date modifiedOn,
        UserResponseDto modifiedBy,
        UserResponseDto createdBy,
        String institutionCode,
        String institutionName
) {
}
