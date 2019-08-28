package org.corbin.client.vo.search;


import lombok.Data;
import org.corbin.common.base.vo.BaseVo;

@Data
public class SearchSongVo extends BaseVo {
    private String searchValue;
}
