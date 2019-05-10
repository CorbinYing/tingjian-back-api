package org.corbin.client.vo.admin;

import lombok.Data;
import org.corbin.common.base.vo.BaseVo;
import org.corbin.common.entity.SongInfo;

import javax.validation.constraints.NotBlank;

@Data
public class AddMusicVo extends BaseVo {

    @NotBlank
    private String songName;
    @NotBlank
    private String singerName;

    private String songDesc;
    @NotBlank
    private Integer songType;
    @NotBlank
    private String songRecommendWord;

    private String songPublishTime;


    public SongInfo convert2DO() {

        SongInfo songInfo = new SongInfo();
        songInfo.setSongName(songName);
        songInfo.setSongDesc(songDesc);
        songInfo.setSongType(songType);
        songInfo.setSongPublishTime(string2Date(songPublishTime));
        songInfo.setSongRecommendWord(songRecommendWord);
        System.out.println(songInfo.getSongPublishTime());
        return songInfo;
    }


}
