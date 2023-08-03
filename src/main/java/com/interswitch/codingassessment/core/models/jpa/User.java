package com.interswitch.codingassessment.core.models.jpa;

import com.interswitch.codingassessment.core.models.enums.CorporateType;
import com.interswitch.codingassessment.core.models.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
@Entity
@Table(name = "t_user"/*, schema = "core"*/)
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity{
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private String emailAddress;
    private UserType userType;
    private CorporateType corporateType;
    private Role role;

    @Column(name = "username", unique = true)
    public String getUsername() {
        return username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "email_address")
    public String getEmailAddress() {
        return emailAddress;
    }

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    public UserType getUserType() {
        return userType;
    }

    @Column(name = "corporate_type")
    @Enumerated(EnumType.STRING)
    public CorporateType getCorporateType() {
        return corporateType;
    }

    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER)
    public Role getRole() {
        return role;
    }
}
