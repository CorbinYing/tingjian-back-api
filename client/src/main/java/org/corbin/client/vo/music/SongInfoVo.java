package org.corbin.client.vo.music;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.corbin.common.base.vo.BaseVo;
import org.corbin.common.entity.SingerInfo;
import org.corbin.common.entity.SongInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SongInfoVo extends BaseVo {

    private String songId;

    private String singerDesc;
    private String singerId;
    private String singerName;

    private String songName;


    private String songLyrics;


    private Integer songType;


    private String songDesc;


    private Date songShelfTime;


    private Date songPublishTime;

    private String songRecommendWord;

    private String songPath;

    private String songImgPath;

    private String songStar;

    private boolean songCollectStatus;

    public static Page<SongInfoVo> convert2PageVO(Page<SongInfo> songInfoPage) {
        if (songInfoPage == null) {
            return null;
        }
        List<SongInfo> songInfoList = songInfoPage.getContent();
        List<SongInfoVo> songInfoVoList = Lists.newArrayList();
        for (SongInfo songInfo : songInfoList) {
            songInfoVoList.add(convert2Vo(songInfo));
        }


        Page<SongInfoVo> page = new PageImpl<SongInfoVo>(songInfoVoList, songInfoPage.getPageable(), songInfoPage.getTotalElements());
        return page;
    }


    public static SongInfoVo convert2Vo(@NonNull SongInfo songInfo) {

        SongInfoVo songInfoVo = new SongInfoVo();
        BeanUtils.copyProperties(songInfo, songInfoVo);

        songInfoVo.setSingerId(songInfo.getSongId().toString());
        songInfoVo.setSongStar(songInfo.getSongStar() == null ? "0" : songInfo.getSongStar().toString());
        songInfoVo.setSongId(songInfo.getSongId().toString());
        songInfoVo.setSongCollectStatus(false);

        SingerInfo singerInfo=songInfo.getSingerInfo();
        songInfoVo.setSingerName(singerInfo==null?null:singerInfo.getSingerName());
        songInfoVo.setSingerDesc(singerInfo==null?null:singerInfo.getSingerDesc());
        return songInfoVo;
    }


    public static SongInfoVo convert2VoWithCollect(SongInfo songInfo,List<SongInfo> collectSongList){
        if (songInfo == null) {
            return null;
        }
        SongInfoVo songInfoVo=convert2Vo(songInfo);
        if (collectSongList != null) {
            for (int i = 0; i < collectSongList.size(); i++) {
                if (collectSongList.get(i).getSongId().toString().equals(songInfoVo.getSongId())){
                    songInfoVo.setSongCollectStatus(true);
                }
            }
        }
        return songInfoVo;

    }
    /**
     * 转为vo，同时标住用户已收藏歌曲
     *
     * @param songInfoPage
     * @param collectSongList
     * @return
     */
    public static Page<SongInfoVo> convert2SongInfoWithCollect(Page<SongInfo> songInfoPage, List<SongInfo> collectSongList) {
        if (songInfoPage == null) {
            return null;
        }

        Page<SongInfoVo> page = convert2PageVO(songInfoPage);
        if (collectSongList != null) {
            for (int i = 0; i < collectSongList.size(); i++) {
                for (SongInfoVo vo : page.getContent()) {
                    if (collectSongList.get(i).getSongId().toString().equals(vo.getSongId())) {
                        vo.setSongCollectStatus(true);
                    }
                }
            }
        }
        return page;
    }
}
