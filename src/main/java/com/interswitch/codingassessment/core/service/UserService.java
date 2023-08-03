package com.interswitch.codingassessment.core.service;

import com.interswitch.codingassessment.commons.exceptions.model.ExceptionType;
import com.interswitch.codingassessment.commons.utils.AuditService;
import com.interswitch.codingassessment.commons.utils.Validate;
import com.interswitch.codingassessment.core.models.dto.AssignRoleDto;
import com.interswitch.codingassessment.core.models.dto.RoleRequestDto;
import com.interswitch.codingassessment.core.models.jpa.Permission;
import com.interswitch.codingassessment.core.models.jpa.Role;
import com.interswitch.codingassessment.core.repository.PermissionRepository;
import com.interswitch.codingassessment.core.repository.RoleRepository;
import com.interswitch.codingassessment.core.repository.UserRepository;
import com.interswitch.codingassessment.security.models.dto.UserResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.interswitch.codingassessment.commons.utils.ConstantMessage.*;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 8/1/23
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuditService auditService;
    private final PermissionRepository permissionRepository;

    @Transactional
    public Role createNewRole(RoleRequestDto roleRequestDto){

        roleRequestDto.validate();
        Validate.isTrue(roleRepository.findByRoleName(roleRequestDto.roleName()).isEmpty(), ExceptionType.BAD_REQUEST, ROLE_EXISTS, roleRequestDto.roleName());
        Validate.isTrue(roleRepository.findByCodeName(roleRequestDto.codeName()).isEmpty(), ExceptionType.BAD_REQUEST, ROLE_CODE_ALREADY_TAKEN, roleRequestDto.codeName());

        var role = Role.builder()
                .codeName(roleRequestDto.codeName())
                .roleName(roleRequestDto.roleName())
                .build();

        auditService.stampLongEntity(role);
        var result = roleRepository.save(role);
        //create permissions
        var permissions = getRolePermissions(result);
        permissionRepository.saveAll(permissions);

        return result;
    }

    public UserResponseDto assignRoleToUser(AssignRoleDto assignRoleDto){

        assignRoleDto.validate();

        var existingUser = userRepository.findById(assignRoleDto.userId());
        var existingRole = roleRepository.findById(assignRoleDto.roleId());

        Validate.isPresent(existingRole, ROLE_NOT_FOUND, assignRoleDto.roleId());
        Validate.isPresent(existingUser, USER_NOT_FOUND, assignRoleDto.userId());

        var role = existingRole.get();
        var user = existingUser.get();

        user.setRole(role);
        auditService.stampLongEntity(user);

        var result = userRepository.save(user);

        return null;
    }

    public Role getUserRole(Long userId){

        var existingRole = roleRepository.findById(userId);
        Validate.isPresent(existingRole, ROLE_NOT_FOUND, userId);

        return existingRole.get();
    }

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    private List<Permission> getRolePermissions(Role role){

        String roleName = role.getRoleName();
        return List.of(
                createPermission(role, roleName.concat(".READ")),
                createPermission(role, roleName.concat(".WRITE")),
                createPermission(role, roleName.concat(".UPDATE")),
                createPermission(role, roleName.concat(".DELETE"))
        );
    }

    private Permission createPermission(Role role, String permission){

        return Permission.builder()
                .role(role)
                .name(permission)
                .build();
    }

}
