package com.interswitch.codingassessment.core.models.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Setter;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
@Entity
@Table(name = "t_bank_branch", schema = "core")
@Setter
public class BankBranch extends BaseEntity{
    private String branchCode;
    private String branchName;

    @Column(name = "branch_code")
    public String getBranchCode() {
        return branchCode;
    }

    @Column(name = "branch_name")
    public String getBranchName() {
        return branchName;
    }
}
