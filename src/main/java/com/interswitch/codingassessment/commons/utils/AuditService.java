package com.interswitch.codingassessment.commons.utils;

import com.interswitch.codingassessment.commons.exceptions.model.ExceptionType;
import com.interswitch.codingassessment.core.models.jpa.AuditEntity;
import com.interswitch.codingassessment.core.models.jpa.BaseEntity;
import com.interswitch.codingassessment.core.models.jpa.User;
import com.interswitch.codingassessment.security.models.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/31/23
 **/
@Service
public class AuditService {

    public void stampLongEntity(BaseEntity entity) {
        Date date = DateTimeUtil.getCurrentUTCTime();
        if(entity.getId() == null){
            entity.setCreatedOn(date);
        }
        entity.setCreatedOn(DateTimeUtil.getCurrentUTCTime());
    }

    public void stampAuditedEntity(AuditEntity auditEntity) {
        var lUser = getLoggedInUser();
        Validate.notNull(lUser, ExceptionType.BAD_CREDENTIALS,"Only a logged in user can make this change");
        Date date = DateTimeUtil.getCurrentUTCTime();
        User user = new User();
        user.setId(lUser.getId());

        if(auditEntity.getId() == null){
            auditEntity.setCreatedOn(date);
            auditEntity.setCreatedBy(user);
        }

        auditEntity.setModifiedBy(user);
        auditEntity.setModifiedOn(date);
    }

    public LoginUser getLoggedInUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.isAuthenticated()){
            return (LoginUser) authentication.getPrincipal();
        }
        return null;
    }
}
