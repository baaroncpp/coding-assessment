package com.interswitch.codingassessment.security.service;

import com.interswitch.codingassessment.commons.utils.AuditService;
import com.interswitch.codingassessment.core.models.enums.CorporateType;
import com.interswitch.codingassessment.core.models.enums.UserType;
import com.interswitch.codingassessment.core.models.jpa.Role;
import com.interswitch.codingassessment.core.models.jpa.User;
import com.interswitch.codingassessment.core.repository.RoleRepository;
import com.interswitch.codingassessment.core.repository.UserRepository;
import com.interswitch.codingassessment.security.models.dto.UserRequestDto;
import com.interswitch.codingassessment.security.models.dto.UserResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 8/3/23
 **/
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private AuditService auditService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    void shouldRegisterUserC() {

        //GIVEN
        var userRequestDto = new UserRequestDto(
                "bkaaron",
                "aaron",
                "buk",
                "aaron",
                "bkg@mail.com",
                "USER_C",
                "OTHERS",
                1L
        );

        Role role = new Role();
        role.setId(1L);
        role.setRoleName("ADMIN_ROLE");
        role.setCodeName("RTE4321");
        var user = User.builder()
                .username(userRequestDto.username())
                .password(passwordEncoder.encode(userRequestDto.password()))
                .lastName(userRequestDto.lastName())
                .firstName(userRequestDto.firstName())
                .emailAddress(userRequestDto.emailAddress())
                .userType(UserType.valueOf(userRequestDto.userType()))
                .corporateType(CorporateType.valueOf(userRequestDto.corporateType()))
                .role(role)
                .build();

        User newUser = user;
        newUser.setCreatedOn(new Date());
        newUser.setModifiedOn(new Date());
        newUser.setId(2L);

        //WHEN
        when(userRepository.findByUsername(userRequestDto.username())).thenReturn(Optional.empty());
        when(userRepository.findByEmailAddress(userRequestDto.emailAddress())).thenReturn(Optional.empty());
        when(roleRepository.findById(userRequestDto.roleId())).thenReturn(Optional.of(role));
        when(userRepository.save(any())).thenReturn(newUser);

        UserResponseDto userResponseDto = authService.registerNewUser(userRequestDto);

        assertThat(userResponseDto.username().equals(userRequestDto.username()));
    }
}