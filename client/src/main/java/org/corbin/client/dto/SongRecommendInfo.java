package org.corbin.client.dto;

import lombok.Getter;
import lombok.Setter;
import org.corbin.common.entity.SongInfo;

import java.io.Serializable;

@Getter
@Setter
public class SongRecommendInfo implements Serializable {
    private Double songRecommend;

    private Long songId;

    private String songName;

    private Integer songType;





    /**
     * 参演歌手的id
     */
    private Long singerId;


    public SongRecommendInfo(SongInfo songInfo){
        this.setSongId(songInfo.getSongId());
        this.setSingerId(songInfo.getSingerId());
        this.setSongType(songInfo.getSongType());
        this.setSongName(songInfo.getSongName());
    }

}
