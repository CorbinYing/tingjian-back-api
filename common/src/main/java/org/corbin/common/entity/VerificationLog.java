package org.corbin.common.entity;

import lombok.Data;
import org.corbin.common.base.entity.AbstractBaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "verification_log")
@EntityListeners(AuditingEntityListener.class)
public class VerificationLog extends AbstractBaseEntity {

    @Column(name = "mail")
    private String mail;

    @Column(name = "tel")
    private String tel;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column(name = "failure_time")
    private Date failureTime;
}
