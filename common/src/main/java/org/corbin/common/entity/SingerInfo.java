package org.corbin.common.entity;

import lombok.Data;
import org.corbin.common.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "singer_info")
public class SingerInfo extends BaseEntity implements Serializable {

    @Column(name = "singer_id")
    private Long singerId;

    @Column(name = "singer_name")
    private String singerName;

    @Column(name = "singer_sex")
    private Integer singerSex;

    /**
     * 英文，隔开
     */
    @Column(name = "singer_type")
    private String singerType;

    @Column(name = "singer_desc")
    private String singerDesc;
}
