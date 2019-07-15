package org.corbin.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.corbin.client.base.controller.BaseClientController;
import org.corbin.client.vo.music.SongInfoVo;
import org.corbin.common.base.Response.ResponseCode;
import org.corbin.common.base.Response.ResponseResult;
import org.corbin.common.base.vo.BaseVo;
import org.corbin.common.entity.SongInfo;
import org.corbin.common.service.CollectInfoService;
import org.corbin.common.service.SongInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/song")
@CrossOrigin(origins = "*",maxAge = 3600)
public class SongInfoController extends BaseClientController {
    @Autowired
    private SongInfoService songInfoService;
    @Autowired
    private CollectInfoService collectInfoService;

    /**
     * 歌曲点赞功能
     *
     * @param songId
     * @return
     */
    @PostMapping("/star")

    public ResponseResult starSong(@RequestParam("songId") Long songId,@RequestParam("userId") Long userId ) {

        recordLoginActiveUpdate(userId);

        SongInfo songInfo = songInfoService.starSong(songId);
        return ResponseResult.newInstance(ResponseCode.SUCC_0, songInfo);
    }

    /**
     * 登录用户收藏歌曲
     *
     * @param songId
     * @param userId
     * @return
     */
    @PostMapping("/add-collect-song")
    public ResponseResult addCollectSong(@RequestParam("songId") Long songId, @RequestParam Long userId) {

        isUserLogin(userId);
        collectInfoService.insertCollectSong(userId, songId);
        return ResponseResult.newInstance(ResponseCode.SUCC_0);
    }


    /**
     * 登录用户删除收藏歌曲
     *
     * @param songId
     * @param userId
     * @return
     */
    @PostMapping("/del-collect-song")
    public ResponseResult delCollectSong(@RequestParam("songId") Long songId, @RequestParam Long userId) {

        isUserLogin(userId);
        collectInfoService.delCollectSong(userId, songId);
        return ResponseResult.newInstance(ResponseCode.SUCC_0);
    }

    /**
     * 用户已收藏歌曲
     *
     * @return
     */
    @PostMapping("/my/collect-song-list")
    public ResponseResult showUserCollectSong(@RequestBody BaseVo vo) {
        isUserLogin(vo);

        Page<SongInfo> songInfoPage = songInfoService.getCollectSongPage(vo.getUserId(),vo.of());
        if (songInfoPage == null) {
            return ResponseResult.newInstance(ResponseCode.SUCC_1);
        }

        Page<SongInfoVo> songInfoVoPage = SongInfoVo.convert2SongInfoWithCollect(songInfoPage,songInfoPage.getContent());
        return ResponseResult.newInstance(ResponseCode.SUCC_0, songInfoVoPage);

    }
}
