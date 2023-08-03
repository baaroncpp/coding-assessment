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
@Table(name = "t_institution"/*, schema = "core"*/)
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Institution extends AuditEntity{
    private String institutionCode;
    private String institutionName;

    @Column(name = "institution_code")
    public String getInstitutionCode() {
        return institutionCode;
    }

    @Column(name = "institution_name")
    public String getInstitutionName() {
        return institutionName;
    }
}
