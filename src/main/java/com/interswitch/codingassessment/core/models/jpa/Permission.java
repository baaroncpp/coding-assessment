package com.interswitch.codingassessment.core.models.jpa;

import jakarta.persistence.*;
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
@Table(name = "t_permission"/*, schema = "core"*/)
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends BaseEntity{
    private Role role;
    private String name;

    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = true, updatable = false)
    @OneToOne(fetch = FetchType.EAGER)
    public Role getRole() {
        return role;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }
}
