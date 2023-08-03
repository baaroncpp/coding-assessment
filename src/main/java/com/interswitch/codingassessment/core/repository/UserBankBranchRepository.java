package com.interswitch.codingassessment.core.repository;

import com.interswitch.codingassessment.core.models.jpa.UserBankBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
@Repository
public interface UserBankBranchRepository extends JpaRepository<UserBankBranch, Long> {
}
