package com.interswitch.codingassessment.core.repository;

import com.interswitch.codingassessment.core.models.jpa.BankBranch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
public interface BankBranchRepository extends JpaRepository<BankBranch, Long> {
}
