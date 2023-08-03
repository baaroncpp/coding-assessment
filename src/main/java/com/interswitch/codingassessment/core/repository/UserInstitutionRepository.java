package com.interswitch.codingassessment.core.repository;

import com.interswitch.codingassessment.core.models.jpa.Institution;
import com.interswitch.codingassessment.core.models.jpa.User;
import com.interswitch.codingassessment.core.models.jpa.UserInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 8/1/23
 **/
@Repository
public interface UserInstitutionRepository extends JpaRepository<UserInstitution, Long> {
    Optional<UserInstitution> findByUserAndInstitution(User user, Institution institution);
    Optional<UserInstitution> findByUser(User user);
}
