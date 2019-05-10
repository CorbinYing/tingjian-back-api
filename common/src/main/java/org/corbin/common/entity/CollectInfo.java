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

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "collect_info")
@EntityListeners(AuditingEntityListener.class)
public class CollectInfo extends BaseEntity implements Serializable {


    @Column(name = "user_id")
    private Long userId;

    @Column(name = "collect_id")
    private Long collectId;


    @Column(name = "collect_type")
    private Integer collectType;

    @Column(name = "collect_song_type")
    private  Integer collectSongType;

}
