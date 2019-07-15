package org.corbin.client.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.corbin.client.vo.music.SongInfoVo;
import org.corbin.common.base.Response.ResponseCode;
import org.corbin.common.base.controller.BaseController;
import org.corbin.common.base.exception.ServiceException;
import org.corbin.common.base.vo.BaseVo;
import org.corbin.common.entity.SongInfo;
import org.corbin.common.entity.UserActiveInfo;
import org.corbin.common.service.SongInfoService;
import org.corbin.common.service.UserActiveInfoService;
import org.corbin.common.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class BaseClientController extends BaseController {
    @Autowired
    private UserActiveInfoService userActiveInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private SongInfoService songInfoService;

    /**
     * 用户登录验证,必须登录
     *
     * @param vo
     */
    public void isUserLogin(BaseVo vo) {
        if (!userActiveInfoService.isUserLogin(vo.getUserId())) {
            throw new ServiceException(ResponseCode.ERR_11003, "请重新登录");
        }
        //更新用户活动时间
        userActiveInfoService.updateUserActive(vo.getUserId());
    }

    /**
     * 用户登录验证,必须登录
     *
     * @param userId
     */
    public void isUserLogin(Long userId) {
        if (!userActiveInfoService.isUserLogin(userId)) {
            throw new ServiceException(ResponseCode.ERR_11003, "请重新登录");
        }

        //更新用户活动时间
        userActiveInfoService.updateUserActive(userId);
    }


    /**
     * 记录用户活动,用户可以不登录
     * userId默认=0 ，不存在的用户
     *
     * @param vo
     */
    public void recordLoginActiveUpdate(BaseVo vo) {
        Assert.notNull(vo.getUserId(), "userI不能为空");
        recordLoginActiveUpdate(vo.getUserId());
    }

    /**
     * 记录用户活动,用户可以不登录
     * userId默认=0 ，不存在的用户
     *
     * @param userId
     */
    public void recordLoginActiveUpdate(@NonNull Long userId) {
        if (userId != null && userId != 0) {
            //用户存在
            if (userInfoService.findByUserId(userId) != null) {
                UserActiveInfo userActiveInfo = userActiveInfoService.findByUserId(userId);
                if (userActiveInfo != null) {
                    userActiveInfo.setUpdateTime(new Date());
                    userActiveInfoService.updateUserActive(userActiveInfo);
                }/* else {
                    throw new ServiceException(ResponseCode.ERR_11003, "请重新登录");
                }*/
            }

        }
    }

    /**
     * 根据用户的登录状态转换歌曲是否被用户收藏
     *
     * @param userId
     * @param page
     * @return
     */
    public Page<SongInfoVo> convertWithCollectStatus(Long userId, Page<SongInfo> page) {
        if (userId == null || userId.longValue() == 0) {
            return SongInfoVo.convert2PageVO(page);
        }

        if (userActiveInfoService.isUserLogin(userId)) {
            List<SongInfo> collectSongInfoList = songInfoService.getCollectSongInfoList(userId);
            Page<SongInfoVo> pageVo = SongInfoVo.convert2SongInfoWithCollect(page, collectSongInfoList);
            return pageVo;
        } else {
            return SongInfoVo.convert2PageVO(page);
        }
    }

}
