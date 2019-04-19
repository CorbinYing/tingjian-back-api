package org.corbin.common.base.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Corbin
 * @Date: 2018/12/4
 * @ClassName: BaseEntity
 * @Descripton:
 */
@MappedSuperclass
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class BaseEntity extends AbstractBaseEntity implements Serializable {

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

}
