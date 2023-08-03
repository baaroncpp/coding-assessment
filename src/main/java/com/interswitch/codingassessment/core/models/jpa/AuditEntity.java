package com.interswitch.codingassessment.core.models.jpa;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/31/23
 **/
@MappedSuperclass
@Setter
@ToString
public class AuditEntity extends BaseEntity {
    private User modifiedBy;
    private User createdBy;

    @JoinColumn(name = "modified_by_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER)
    public User getModifiedBy() {
        return modifiedBy;
    }

    @JoinColumn(name = "created_by_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER)
    public User getCreatedBy() {
        return createdBy;
    }
}

