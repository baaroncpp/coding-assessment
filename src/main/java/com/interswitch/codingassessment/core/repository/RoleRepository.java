package com.interswitch.codingassessment.core.repository;

import com.interswitch.codingassessment.core.models.jpa.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByCodeName(String codeName);
    Optional<Role> findByRoleName(String roleName);
}
