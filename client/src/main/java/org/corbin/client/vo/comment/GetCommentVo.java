package org.corbin.client.vo.comment;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.corbin.common.base.vo.EssentialVo;
import org.corbin.common.entity.CommentInfo;

import java.util.List;

@Getter
@Setter
public class GetCommentVo extends EssentialVo {
    private String commentContent;
    private String songId;
    /**
     * 头像地址,后期添加
     */
   // private String headImgPath;

    public static GetCommentVo convert(CommentInfo commentInfo){
        if (commentInfo==null){return null;}
        GetCommentVo vo=new GetCommentVo();
        vo.setCommentContent(commentInfo.getCommentContent());
        vo.setSongId(commentInfo.getSongId().toString());
        return vo;
    }
    public static List<GetCommentVo> convert(List<CommentInfo> list){
        if (list==null){return null;}
        List<GetCommentVo> voList= Lists.newArrayList();
        for(CommentInfo commentInfo: list){
            voList.add(convert(commentInfo));
        }
        return voList;
    }
}
