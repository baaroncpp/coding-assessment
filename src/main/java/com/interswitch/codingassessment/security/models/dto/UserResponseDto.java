package com.interswitch.codingassessment.security.models.dto;

import com.interswitch.codingassessment.core.models.enums.CorporateType;
import com.interswitch.codingassessment.core.models.enums.UserType;
import com.interswitch.codingassessment.core.models.jpa.Role;
import com.interswitch.codingassessment.core.models.jpa.User;

import java.util.Date;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/31/23
 **/
public record UserResponseDto(
        Long id,
        Date createdOn,
        Date modifiedOn,
        String username,
        String lastName,
        String firstName,
        String emailAddress,
        UserType userType,
        CorporateType corporateType,
        Role role
) {
}
