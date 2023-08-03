package com.interswitch.codingassessment.security.service;

import com.interswitch.codingassessment.commons.exceptions.ResourceNotFoundException;
import com.interswitch.codingassessment.core.repository.PermissionRepository;
import com.interswitch.codingassessment.core.repository.UserRepository;
import com.interswitch.codingassessment.security.models.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return getLoginUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private Optional<LoginUser> getLoginUser(String username){
        var user = userRepository.findByUsername(username).orElseThrow(
                ()-> new ResourceNotFoundException("User not found")
        );
        var permissions = permissionRepository.findAllByRole(user.getRole());

        var authorities = permissions.stream()
                .map(val -> new SimpleGrantedAuthority(val.getName()))
                .collect(Collectors.toSet());

        var loginUser = LoginUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .accountNonExpired(Boolean.TRUE)
                .accountNonLocked(Boolean.TRUE)
                .enabled(Boolean.TRUE)
                .credentialsNonExpired(Boolean.TRUE)
                .authorities(authorities)
                .build();

        return Optional.of(loginUser);
    }
}
