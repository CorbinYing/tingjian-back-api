package org.corbin.client.controller.admin;

import org.corbin.client.base.controller.BaseClientController;
import org.corbin.client.service.SingerInfoService;
import org.corbin.client.service.SongInfoService;
import org.corbin.common.base.Response.ResponseCode;
import org.corbin.common.base.Response.ResponseResult;
import org.corbin.common.base.vo.BaseVo;
import org.corbin.common.entity.SingerInfo;
import org.corbin.common.entity.SongInfo;
import org.corbin.common.util.IdHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/admin")
public class AddMusicController extends BaseClientController {
    @Autowired
    private SingerInfoService singerInfoService;
    @Autowired
    private SongInfoService songInfoService;

    /**
     * 图片未上传，
     *
     * @param file
     * @param songName
     * @param singerName
     * @param songDesc
     * @param songType
     * @param songRecommendWord
     * @param songPublishTime
     * @return
     */
    @PostMapping("/add/music")
    public ResponseResult addMusic(@RequestParam("file") MultipartFile file,
                                   @RequestParam String songName, @RequestParam String singerName,
                                   @RequestParam String songDesc, @RequestParam Integer songType,
                                   @RequestParam String songRecommendWord, @RequestParam String songPublishTime) {

        String basePath="/home/yin/WebstormProjects/tingjian";
        String songPath="/static/music/" + singerName + "/" + file.getOriginalFilename();
        File localFile = createFile(basePath+songPath);
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //歌手信息
        SingerInfo singerInfo = singerInfoService.findBySingerName(singerName);
        if (singerInfo == null) {
            singerInfo = new SingerInfo();
            singerInfo.setSingerId(IdHelper.snowflake.nextId2Long());
            singerInfo.setSingerName(singerName);

            singerInfoService.insertSinger(singerInfo);
        }

        //新增song info
        SongInfo songInfo = new SongInfo();
        songInfo.setSongId(IdHelper.snowflake.nextId2Long());
        songInfo.setSingerId(singerInfo.getSingerId());
        songInfo.setSongName(songName);
        songInfo.setSongDesc(songDesc);
        songInfo.setSongType(songType);
        songInfo.setSongShelfTime(new Date());
        songInfo.setSongPublishTime(BaseVo.string2Date(songPublishTime));
        songInfo.setSongRecommendWord(songRecommendWord);
        songInfo.setSongPath(songPath);

        songInfoService.insertSong(songInfo);

        return ResponseResult.newInstance(ResponseCode.SUCC_0);

    }

}
