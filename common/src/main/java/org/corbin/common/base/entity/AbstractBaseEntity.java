package org.corbin.common.base.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Corbin
 * @Date: 2018/12/4
 * @ClassName: AbstractBaseEntity
 * @Descripton:
 */
@MappedSuperclass
@DynamicInsert
/**
 * @DynamicUpdate属性:设置为true,设置为true,表示update对象的时候,生成动态的update语句,如果这个字段的值是null就不会被加入到update语句中,默认false。
 */
@DynamicUpdate
@Getter
@Setter
public abstract class AbstractBaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

}
