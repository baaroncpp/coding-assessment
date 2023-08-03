package com.interswitch.codingassessment.commons.utils;

import com.interswitch.codingassessment.core.models.dto.InstitutionResponseDto;
import com.interswitch.codingassessment.core.models.dto.UserInstitutionDto;
import com.interswitch.codingassessment.core.models.enums.CorporateType;
import com.interswitch.codingassessment.core.models.enums.UserType;
import com.interswitch.codingassessment.core.models.jpa.Institution;
import com.interswitch.codingassessment.core.models.jpa.Role;
import com.interswitch.codingassessment.core.models.jpa.User;
import com.interswitch.codingassessment.core.models.jpa.UserInstitution;
import com.interswitch.codingassessment.security.models.dto.UserResponseDto;

import java.util.Date;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 8/2/23
 **/
public class DtoService {
    private DtoService() { }

    public static UserResponseDto userToDto(User user){
        return new UserResponseDto(
                user.getId(),
                user.getCreatedOn(),
                user.getModifiedOn(),
                user.getUsername(),
                user.getLastName(),
                user.getFirstName(),
                user.getEmailAddress(),
                user.getUserType(),
                user.getCorporateType(),
                user.getRole()
        );
    }

    public static InstitutionResponseDto institutionToDto(Institution result){

        return new InstitutionResponseDto(
                result.getId(),
                result.getCreatedOn(),
                result.getModifiedOn(),
                userToDto(result.getModifiedBy()),
                userToDto(result.getCreatedBy()),
                result.getInstitutionCode(),
                result.getInstitutionName()
        );
    }

    public static UserInstitutionDto userInstitutionToDto(UserInstitution userInstitution){

        return new UserInstitutionDto(
                userInstitution.getId(),
                userInstitution.getCreatedOn(),
                userInstitution.getModifiedOn(),
                userToDto(userInstitution.getModifiedBy()),
                userToDto(userInstitution.getCreatedBy()),
                userToDto(userInstitution.getUser()),
                institutionToDto(userInstitution.getInstitution())
        );
    }
}
