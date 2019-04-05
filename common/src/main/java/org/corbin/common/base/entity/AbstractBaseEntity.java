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
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CREATE_TIME")
    private Date createTime;

}
