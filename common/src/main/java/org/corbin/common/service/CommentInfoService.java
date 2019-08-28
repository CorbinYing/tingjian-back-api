package org.corbin.common.service;

import lombok.extern.slf4j.Slf4j;
import org.corbin.common.base.service.BaseService;
import org.corbin.common.entity.CommentInfo;
import org.corbin.common.repository.*;
import org.corbin.common.util.IdHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CommentInfoService extends BaseService<CommentInfo,Long> {
    @Autowired
    private CollectInfoRepository collectInfoRepository;
    @Autowired
    private CommentInfoRepository commentInfoRepository;
    @Autowired
    private SingerInfoRepository singerInfoRepository;
    @Autowired
    private SongInfoRepository songInfoRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;


    /**
     * 添加评论
     *
     * @param commentInfo
     * @return
     */
    public CommentInfo insertComment(@NonNull CommentInfo commentInfo) {
        return commentInfoRepository.saveAndFlush(commentInfo);
    }

    public CommentInfo insertComment(@NonNull Long userId, @NonNull String content, @NonNull Long songId) {
        if (userInfoRepository.findByUserId(userId) == null) {
            log.error("userId={} 不存在", userId);
            return null;
        }

        if (songInfoRepository.findBySongId(songId) == null) {
            log.error("songId={} 不存在", songId);
            return null;
        }


        CommentInfo commentInfo = new CommentInfo();
        commentInfo.setCommentContent(content);
        commentInfo.setCommentId(IdHelper.snowflake.nextId2Long());
        commentInfo.setSongId(songId);
        commentInfo.setUserId(userId);

        return insertComment(commentInfo);
    }

    /**
     * @param songId
     * @return
     */
    public List<CommentInfo> findCommentBySongId(@NonNull Long songId) {
        return commentInfoRepository.findAllBySongId(songId);
    }
}
