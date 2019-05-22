package org.corbin.client.vo.comment;

import lombok.Getter;
import lombok.Setter;
import org.corbin.common.base.vo.EssentialVo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * userId
 * songId
 * commentContent
 */
@Getter
@Setter
public class AddCommentVo extends EssentialVo {
    @NotBlank(message = "songId not be null")
    private Long songId;

    @NotNull(message = "commentContent not be null")
    private String commentContent;
}
