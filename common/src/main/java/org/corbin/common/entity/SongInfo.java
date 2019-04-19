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
import java.util.Date;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Table(name = "song_info")
public class SongInfo extends BaseEntity implements Serializable {

    @Column(name = "song_id")
    private Long songId;

    @Column(name = "song_name")
    private String songName;

    @Column(name = "song_lyrics")
    private String songLyrics;

    /**
     * 英文，隔开
     */
    @Column(name = "song_type")
    private String songType;

    @Column(name = "song_star")
    private Integer songStar;

    @Column(name = "song_desc")
    private String songDesc;

    @Column(name = "song_publish_time")
    private Date songPublishTime;

    @Column(name = "song_shelf_time")
    private Date songShelfTime;

    /**
     * 参演歌手的id list，以英文“，”隔开
     */
    @Column(name = "singer_id_list")
    private String singers;


}
