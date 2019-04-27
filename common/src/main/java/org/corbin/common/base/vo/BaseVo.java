package org.corbin.common.base.vo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class BaseVo {
    /**
     * 用户的id，此属性可以为空
     * 为空时，默认用户未登录，
     * 不为空，且用户存在时，默认用户以登录，
     * 数据库默认用户的误操作登录时长为30min，
     * 超过30分钟，自动下线，需要重新登录
     */
    protected Long userId;

    protected int pageNum;
    protected int pageSize=10;

    /**
     * 获得分页参数
     * @return
     */
    public Pageable of() {
        return PageRequest.of(this.pageNum, this.pageSize);
    }
}
