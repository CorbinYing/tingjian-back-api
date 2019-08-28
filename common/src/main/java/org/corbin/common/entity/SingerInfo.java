package org.corbin.common.entity;

import lombok.Data;
import org.corbin.common.base.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Table(name = "singer_info")
public class SingerInfo extends BaseEntity implements Serializable {

    @Column(name = "singer_id")
    private Long singerId;

    @Column(name = "singer_name")
    private String singerName;

    @Column(name = "singer_sex")
    private Integer singerSex;


    @Column(name = "singer_type")
    private Integer singerType;

    @Column(name = "singer_desc")
    private String singerDesc;

//    @OneToMany(mappedBy = "singerInfo")
//    private List<SongInfo> songInfoList;


}
