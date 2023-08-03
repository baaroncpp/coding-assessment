package com.interswitch.codingassessment.core.repository;

import com.interswitch.codingassessment.core.models.jpa.Permission;
import com.interswitch.codingassessment.core.models.jpa.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findAllByRole(Role role);
}
