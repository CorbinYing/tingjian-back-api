package org.corbin.client.controller;

import org.corbin.client.service.SongInfoService;
import org.corbin.client.service.SongStatisticsDayLogService;
import org.corbin.client.service.UserInfoService;
import org.corbin.common.base.Response.ResponseCode;
import org.corbin.common.base.Response.ResponseResult;
import org.corbin.common.base.vo.BaseVo;
import org.corbin.common.entity.SongInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/music")
@RestController
public class MusicController {
    @Autowired
    private SongInfoService songInfoService;
    @Autowired
    private SongStatisticsDayLogService songStatisticsDayLogService;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 今日热点推荐，collect：star：listen=5:3:2
     *
     * @return
     */
    @PostMapping("/hot/recommend-list")
    ResponseResult songListRecommendToday(@RequestBody BaseVo vo) {
        Pageable pageable = vo.of();

        Page<SongInfo> page = songInfoService.songListRecommendToday(pageable);
        return ResponseResult.newInstance(ResponseCode.SUCC_0, page);

    }


    /**
     * 新歌速递
     *
     * @return
     */
    @PostMapping("/last/song-list")
    ResponseResult newSongList(@RequestBody BaseVo vo) {

        Page<SongInfo> page = songInfoService.getLast30DaysSongList(vo.of());
        return ResponseResult.newInstance(ResponseCode.SUCC_0, page);
    }

    /**
     * 热门神曲
     *
     * @return
     */
    @PostMapping("/hot/song-list")
    ResponseResult hotSongList(@RequestBody BaseVo vo) {

        Pageable pageable = vo.of();
        Page<SongInfo> page = songInfoService.songListHotToday(pageable);
        return ResponseResult.newInstance(ResponseCode.SUCC_0, page);

    }

    /**
     * 用户收藏的歌曲
     *
     * @param vo
     * @return
     */
    @PostMapping("/my/collect")
    ResponseResult myCollectSong(@RequestBody BaseVo vo) {

      //  userInfoService.find
        return null;
    }

}
