package com.interswitch.codingassessment.security.service;

import com.interswitch.codingassessment.commons.exceptions.model.ExceptionType;
import com.interswitch.codingassessment.commons.utils.AuditService;
import com.interswitch.codingassessment.commons.utils.Validate;
import com.interswitch.codingassessment.core.models.enums.CorporateType;
import com.interswitch.codingassessment.core.models.enums.UserType;
import com.interswitch.codingassessment.core.models.jpa.Role;
import com.interswitch.codingassessment.core.models.jpa.User;
import com.interswitch.codingassessment.core.repository.RoleRepository;
import com.interswitch.codingassessment.core.repository.UserRepository;
import com.interswitch.codingassessment.security.models.dto.AuthRequestDto;
import com.interswitch.codingassessment.security.models.dto.AuthResponseDto;
import com.interswitch.codingassessment.security.models.dto.UserRequestDto;
import com.interswitch.codingassessment.security.models.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import static com.interswitch.codingassessment.commons.utils.ConstantMessage.*;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/31/23
 **/
@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuditService auditService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthResponseDto signIn(AuthRequestDto authRequestDto) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.username(), authRequestDto.password()));
        var user = customUserDetailsService.loadUserByUsername(authRequestDto.username());

        var jwt = jwtService.generateToken(user);

        return new AuthResponseDto(
                jwt
        );
    }

    public UserResponseDto registerNewUser(UserRequestDto userRequestDto){

        userRequestDto.validate();

        Validate.isTrue(userRepository.findByUsername(userRequestDto.username()).isEmpty(), ExceptionType.BAD_REQUEST, USERNAME_TAKEN);
        Validate.isTrue(userRepository.findByEmailAddress(userRequestDto.emailAddress()).isEmpty(), ExceptionType.BAD_REQUEST, EMAIL_TAKEN);

        var existingRole = roleRepository.findById(userRequestDto.roleId());
        Validate.isPresent(existingRole, ROLE_NOT_FOUND, userRequestDto.roleId());

        Role role = new Role();
        if(existingRole.isPresent()) {
            role = existingRole.get();
        }

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

        if(userRequestDto.userType().equals(UserType.USER_C.name())){
            role.setId(1L);
            user.setRole(role);
        }

        Validate.isTrue(role.getRoleName().equals("ADMIN_ROLE") && (user.getUserType().equals(UserType.USER_C)),
                ExceptionType.BAD_REQUEST,
                "Only USER_C can have the configuration role");

        auditService.stampLongEntity(user);

        var result = userRepository.save(user);

        return new UserResponseDto(
                result.getId(),
                result.getCreatedOn(),
                result.getModifiedOn(),
                result.getUsername(),
                result.getLastName(),
                result.getFirstName(),
                result.getEmailAddress(),
                result.getUserType(),
                result.getCorporateType(),
                result.getRole()
        );
    }
}
