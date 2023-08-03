package com.interswitch.codingassessment.core.repository;

import com.interswitch.codingassessment.core.models.jpa.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    Optional<Institution> findByInstitutionCode(String institutionCode);
}
