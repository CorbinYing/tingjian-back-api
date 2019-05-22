package org.corbin.common.entity;

import lombok.Data;
import org.corbin.common.base.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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

    /**
     * 参演歌手的id
     */
    @Column(name = "singer_id")
    private Long singerId;

    @Column(name = "song_name")
    private String songName;

    @Column(name = "song_lyrics")
    private String songLyrics;

    @Column(name = "song_type")
    private Integer songType;

    @Column(name = "song_desc")
    private String songDesc;

    @Column(name = "song_shelf_time")
    private Date songShelfTime;

    @Column(name = "song_publish_time")
    private Date songPublishTime;


    @Column(name = "song_recommend_word")
    private String songRecommendWord;

    @Column(name = "song_path")
    private String songPath;

    @Column(name = "song_img_path")
    private String songImgPath;

    /**
     * 歌曲点赞数
     */
    @Column(name = "song_star")
    private Long songStar;

    /**
     * 关联歌手信息
     */
    @ManyToOne()
    @JoinColumn(name = "singer_id",referencedColumnName = "singer_id",insertable = false,updatable = false)
    private SingerInfo singerInfo;


}
