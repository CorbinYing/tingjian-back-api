package org.corbin.common.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

/**
 * @author yin
 * @date 2019/06/15
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID>,
        JpaSpecificationExecutor<T>,
        PagingAndSortingRepository<T, ID> {

    /**
     * 查询多个属性
     * 返回List<Object[]>数组形式的List，数组中内容按照查询字段先后
     *
     * @param hql HQL语句
     * @return
     */
    List<T> queryArrayListByHql(String hql);

    /**
     * 根据hql语句查询一个实体
     *
     * @param hql
     * @return
     */
    T queryOneByHql(String hql);
}
