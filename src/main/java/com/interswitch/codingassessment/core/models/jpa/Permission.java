package com.interswitch.codingassessment.core.models.jpa;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/28/23
 **/
@Entity
@Table(name = "t_permission", schema = "core")
@Setter
public class Permission extends BaseEntity{
    private Role role;
    private String name;

    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = true, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public Role getRole() {
        return role;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }
}
