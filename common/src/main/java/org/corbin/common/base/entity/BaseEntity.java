package org.corbin.common.base.entity;

import javax.persistence.Column;
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
public abstract class BaseEntity extends AbstractBaseEntity implements Serializable {

    @Column(name = "update_time")
    private Date updateTime;

}
