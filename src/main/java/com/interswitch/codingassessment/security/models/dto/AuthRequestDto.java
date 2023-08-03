package com.interswitch.codingassessment.security.models.dto;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/31/23
 **/
public record AuthRequestDto(
        String username,
        String password
) {
}
