package org.corbin.common.base.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EssentialVo {
    /**
     * 用户的id，此属性可以为空
     * 为空时，默认用户未登录，
     * 不为空，且用户存在时，默认用户以登录，
     * 数据库默认用户的误操作登录时长为30min，
     * 超过30分钟，自动下线，需要重新登录
     */
    protected Long userId;
}
