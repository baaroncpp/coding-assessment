package com.interswitch.codingassessment.core.api;

import com.interswitch.codingassessment.core.models.dto.AssignRoleDto;
import com.interswitch.codingassessment.core.models.dto.RoleRequestDto;
import com.interswitch.codingassessment.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 8/1/23
 **/
@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @PostMapping(path = "role", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object createRole(@RequestBody RoleRequestDto roleRequestDto){
        return userService.createNewRole(roleRequestDto);
    }

    @PostMapping(path = "assign/user-role", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object assignUserRole(@RequestBody AssignRoleDto assignRoleDto){
        return userService.assignRoleToUser(assignRoleDto);
    }

    @GetMapping(path = "role/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getRole(@PathVariable("id") Long id){
        return userService.getUserRole(id);
    }

    @GetMapping(path = "roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getRoles(){
        return userService.getAllRoles();
    }
}
