package com.interswitch.codingassessment.commons.utils;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
public class ConstantMessage {

    private ConstantMessage() { }

    public static final String NULL_INST_CODE = "institutionCode is null or empty";
    public static final String NULL_INST_NAME = "institutionName is null or empty";
    public static final String INST_CODE_ALREADY_EXISTS = "institution with code: %s already exists";
    public static final String INST_NOT_FOUND = "institution with ID: %s not found";
    public static final String NULL_USERNAME = "username is null or empty";
    public static final String NULL_PASSWORD = "password is null or empty";
    public static final String NULL_LAST_NAME = "lastName is null or empty";
    public static final String NULL_FIRST_NAME = "firstName is null or empty";
    public static final String NULL_EMAIL = "emailAddress is null or empty";
    public static final String NULL_USER_TYPE = "userType is null or empty";
    public static final String NULL_CORPORATE_TYPE = "corporateType is null or empty";
    public static final String INVALID_USER_TYPE = "Invalid userType, options: USER_A, USER_B, USER_C";
    public static final String INVALID_CORPORATE_TYPE = "Invalid corporateType, options: BANK, OTHERS";
    public static final String INVALID_MAIL = "%s is an invalid email";
    public static final String USERNAME_TAKEN = "Username taken";
    public static final String EMAIL_TAKEN = "Email taken";
    public static final String NULL_ROLE_NAME = "roleName is null or empty";
    public static final String NULL_CODE_NAME = "codeName is null or empty";
    public static final String ROLE_EXISTS = "role %s exists";
    public static final String ROLE_CODE_ALREADY_TAKEN = "code %s already used";
    public static final String ROLE_NOT_FOUND = "role with ID: %s not found";
    public static final String INSTITUTION_NOT_FOUND = "institution with ID: %s not found";
    public static final String NULL_ROLE_ID = "roleId is null";
    public static final String NULL_USER_ID = "userId is null";
    public static final String NULL_INSTITUTION_ID = "institutionId is null";
    public static final String USER_NOT_FOUND = "user with ID: %s not found";
    public static final String USER_C_ONLY_ONE_INST = "USER_C can only belong to one institute";
    public static final String USER_ALREADY_ASSIGNED_INST = "user already assigned to institution";
}
