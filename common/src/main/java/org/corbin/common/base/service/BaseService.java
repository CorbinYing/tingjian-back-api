package org.corbin.common.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BaseService {

    public <T> List<T> getContent(Page<T> page){
        return page.getContent();
    }

    public <T> Page<T> pageImpl(List<T> list, Pageable pageable){
        Page<T> page= new PageImpl<T>(list,pageable,list.size());
        return page;
    }
}
