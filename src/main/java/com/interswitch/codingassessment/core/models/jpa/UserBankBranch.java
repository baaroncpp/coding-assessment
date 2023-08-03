package com.interswitch.codingassessment.core.models.jpa;

import jakarta.persistence.*;
import lombok.Setter;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
@Entity
@Table(name = "t_user_bank_branch",
        /*schema = "core",*/
        uniqueConstraints =
                {
                        @UniqueConstraint(columnNames = {"user_id", "bank_branch_id"})
                })
@Setter
public class UserBankBranch extends AuditEntity{
    private User user;
    private BankBranch bankBranch;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER)
    public User getUser() {
        return user;
    }

    @JoinColumn(name = "bank_branch_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER)
    public BankBranch getBankBranch() {
        return bankBranch;
    }
}
