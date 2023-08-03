package com.interswitch.codingassessment.security.api;

import com.interswitch.codingassessment.security.models.dto.AuthRequestDto;
import com.interswitch.codingassessment.security.models.dto.UserRequestDto;
import com.interswitch.codingassessment.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/31/23
 **/
@RestController
@RequestMapping("api/v1/auth/")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthService authService;

    @PostMapping(path = "get/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAuthToken(@RequestBody AuthRequestDto authRequestDto){
        return authService.signIn(authRequestDto);
    }

    @PostMapping(path = "register/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object registerUser(@RequestBody UserRequestDto userRequestDto){
        return authService.registerNewUser(userRequestDto);
    }

}
