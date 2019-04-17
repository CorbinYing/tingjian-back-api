package org.corbin.common.base.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Corbin
 * @Date: 2018/12/4
 * @ClassName: AbstractBaseEntity
 * @Descripton:
 */
@MappedSuperclass
public abstract class AbstractBaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "create_time")
    private Date createTime;

}
