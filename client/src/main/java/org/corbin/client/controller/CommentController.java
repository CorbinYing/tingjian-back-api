package org.corbin.client.controller;

import org.corbin.client.base.controller.BaseClientController;
import org.corbin.client.service.CommentInfoService;
import org.corbin.client.vo.comment.AddCommentVo;
import org.corbin.client.vo.comment.GetCommentVo;
import org.corbin.common.base.Response.ResponseCode;
import org.corbin.common.base.Response.ResponseResult;
import org.corbin.common.entity.CommentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "*",maxAge = 3600)
public class CommentController extends BaseClientController {
    @Autowired
    private CommentInfoService commentInfoService;

    /**
     * 添加评论
     *
     * @param vo
     * @return
     */
    @PostMapping("/add/song-comment")
    public ResponseResult commentSong(@RequestBody AddCommentVo vo) {
        //校验用户是否登录
        isUserLogin(vo.getUserId());
        //添加评论
        CommentInfo commentInfo = commentInfoService.insertComment(vo.getUserId(), vo.getCommentContent(), vo.getSongId());
        return ResponseResult.newInstance(ResponseCode.SUCC_0, commentInfo);
    }

    @PostMapping("/get/song-comment")
    public ResponseResult searchSongComment(@RequestBody GetCommentVo vo) {
        recordLoginActiveUpdate(vo.getUserId());

       List<CommentInfo> commentInfoList= commentInfoService.findCommentBySongId(Long.valueOf(vo.getSongId()));
       List<GetCommentVo> commentVoList=GetCommentVo.convert(commentInfoList);
       return ResponseResult.newInstance(ResponseCode.SUCC_0,commentVoList);
    }
}
