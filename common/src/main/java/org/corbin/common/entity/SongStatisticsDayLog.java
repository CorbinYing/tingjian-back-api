package org.corbin.common.entity;

import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Table(name = "song_statistics_day_log")
public class SongStatisticsDayLog extends BaseEntity implements Serializable {

    @Column(name = "song_id")
    private Long songId;

    @Column(name = "play_num_y")
    private Integer playTimesYesterday;

    @Column(name = "play_num_t")
    private Integer playTimesToday;

    @Column(name = "star_num_y")
    private Integer starTimesYesterday;

    @Column(name = "star_num_t")
    private Integer starTimesToday;


    @Column(name = "collect_num_y")
    private Integer collectTimesYesterday;

    @Column(name = "collect_num_t")
    private Integer collectTimesToday;

    /**
     * 今日歌曲热度
     */
    @Column(name = "hot_point")
    private Float hotPoint;

    /**
     * 今日歌曲推荐指数
     */
    @Column(name = "recommend_point")
    private Float recommendPoint;

}
