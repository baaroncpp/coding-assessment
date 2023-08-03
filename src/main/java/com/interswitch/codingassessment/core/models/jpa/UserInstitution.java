package com.interswitch.codingassessment.core.models.jpa;

import jakarta.persistence.*;
import lombok.*;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 8/1/23
 **/
@Entity
@Table(name = "t_user_institution"/*, schema = "core"*/)
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInstitution extends AuditEntity{
    private User user;
    private Institution institution;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER)
    public User getUser() {
        return user;
    }

    @JoinColumn(name = "institution_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER)
    public Institution getInstitution() {
        return institution;
    }
}
