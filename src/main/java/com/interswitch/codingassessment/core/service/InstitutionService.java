package com.interswitch.codingassessment.core.service;

import com.interswitch.codingassessment.commons.exceptions.model.ExceptionType;
import com.interswitch.codingassessment.commons.utils.AuditService;
import com.interswitch.codingassessment.commons.utils.DtoService;
import com.interswitch.codingassessment.commons.utils.Validate;
import com.interswitch.codingassessment.core.models.dto.AssignInstitutionDto;
import com.interswitch.codingassessment.core.models.dto.InstitutionRequestDto;
import com.interswitch.codingassessment.core.models.dto.InstitutionResponseDto;
import com.interswitch.codingassessment.core.models.dto.UserInstitutionDto;
import com.interswitch.codingassessment.core.models.enums.UserType;
import com.interswitch.codingassessment.core.models.jpa.Institution;
import com.interswitch.codingassessment.core.models.jpa.User;
import com.interswitch.codingassessment.core.models.jpa.UserInstitution;
import com.interswitch.codingassessment.core.repository.InstitutionRepository;
import com.interswitch.codingassessment.core.repository.UserInstitutionRepository;
import com.interswitch.codingassessment.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

import static com.interswitch.codingassessment.commons.utils.ConstantMessage.*;
import static com.interswitch.codingassessment.commons.utils.DtoService.institutionToDto;
import static com.interswitch.codingassessment.commons.utils.DtoService.userInstitutionToDto;


/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/31/23
 **/
@Slf4j
@RequiredArgsConstructor
@Service
public class InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final UserRepository userRepository;
    private final UserInstitutionRepository userInstitutionRepository;
    private final AuditService auditService;

    public Object addInstitution(InstitutionRequestDto institutionRequestDto){

        institutionRequestDto.validate();

        var existingInstitute = institutionRepository.findByInstitutionCode(institutionRequestDto.institutionCode());
        Validate.isTrue(existingInstitute.isEmpty(), ExceptionType.BAD_REQUEST, INST_CODE_ALREADY_EXISTS, institutionRequestDto.institutionCode());

        var institution = Institution.builder()
                .institutionCode(institutionRequestDto.institutionCode())
                .institutionName(institutionRequestDto.institutionName())
                .build();

        auditService.stampAuditedEntity(institution);

        var result = institutionRepository.save(institution);

        return institutionToDto(result);
    }

    public UserInstitutionDto assignInstitutionToUser(AssignInstitutionDto assignInstitutionDto){

        assignInstitutionDto.validate();

        var existingUser = userRepository.findById(assignInstitutionDto.userId());
        var existingInstitution = institutionRepository.findById(assignInstitutionDto.institutionId());

        Validate.isPresent(existingUser, USER_NOT_FOUND, assignInstitutionDto.userId());
        Validate.isPresent(existingInstitution, INSTITUTION_NOT_FOUND, assignInstitutionDto.institutionId());

        var user = existingUser.get();
        var institution = existingInstitution.get();

        var instC = userInstitutionRepository.findByUser(user);

        if(user.getUserType().equals(UserType.USER_C)){
            Validate.isTrue(instC.isEmpty(), ExceptionType.BAD_REQUEST, USER_C_ONLY_ONE_INST);
        }

        var inst = userInstitutionRepository.findByUserAndInstitution(user, institution);
        Validate.isTrue(inst.isEmpty(), ExceptionType.BAD_REQUEST, USER_ALREADY_ASSIGNED_INST);

        var userInstitute = UserInstitution.builder()
                .institution(existingInstitution.get())
                .user(existingUser.get())
                .build();

        userInstitute.setInstitution(institution);
        userInstitute.setUser(user);

        auditService.stampAuditedEntity(userInstitute);

        var result = userInstitutionRepository.save(userInstitute);

        return userInstitutionToDto(result);
    }

    public Object getInstituteById(Long id){

        var existingInstitute = institutionRepository.findById(id);
        Validate.isPresent(existingInstitute, INST_NOT_FOUND, id);

        var result =  existingInstitute.get();

        return institutionToDto(result);
    }

    public Object getInstituteAll(){
        return institutionRepository.findAll().stream()
                .map(DtoService::institutionToDto)
                .collect(Collectors.toList());
    }

}
