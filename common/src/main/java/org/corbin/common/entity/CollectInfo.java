package org.corbin.common.entity;

import lombok.Data;
import org.corbin.common.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "collect_info")


public class CollectInfo extends BaseEntity implements Serializable {


    @Column(name = "collect_id")
    private Long collectId;


    @Column(name = "collect_type")
    private Integer collectType;

}
