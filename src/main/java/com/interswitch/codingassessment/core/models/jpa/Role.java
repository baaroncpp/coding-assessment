package com.interswitch.codingassessment.core.models.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/28/23
 **/
@Entity
@Table(name = "t_role"/*, schema = "core"*/)
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity{
    private String roleName;
    private String codeName;
    private String roleDescription;

    @Column(name = "role_name", unique = true)
    public String getRoleName() {
        return roleName;
    }

    @Column(name = "code_name", unique = true)
    public String getCodeName() {
        return codeName;
    }

    @Column(name = "role_description")
    public String getRoleDescription() {
        return roleDescription;
    }
}
