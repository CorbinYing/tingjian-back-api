package org.corbin.common.base.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @Author: Corbin
 * @Date: 2018/12/4
 * @ClassName: BaseEntity
 * @Descripton:
 */
@MappedSuperclass
public abstract class BaseEntity extends AbstractBaseEntity{

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

}
