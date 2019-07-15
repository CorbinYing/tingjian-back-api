package org.corbin.common.base.dao;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * @author yin
 * @date 2019/06/15
 */
public class BaseRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID>
        implements BaseRepository<T, ID> {
    /**
     * 父类没有不带参数的构造方法，这里手动构造父类
     */
    private final EntityManager em;
    private Class<T> domainClass;


    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.domainClass=domainClass;
        this.em = em;
    }

    /**
     * 查询多个属性
     * 返回List<Object[]>数组形式的List，数组中内容按照查询字段先后
     *
     * @param hql HQL语句
     * @return
     */
    @Override
    public List<T> queryArrayListByHql(String hql) {
        Query query = em.createQuery(hql);
        List<T> list = query.getResultList();
        return list;
    }

    /**
     * 根据hql语句查询一个实体
     *
     * @param hql
     * @return
     */
    @Override
    public T queryOneByHql(String hql) {
        Query query = em.createQuery(hql);
        return (T) query.getSingleResult();
    }

    //通过EntityManager来完成查询
/*    @Override
    public  List<T> listBySQL(String sql) {
        return entityManager.createNativeQuery(sql).getResultList();
    }*/


/*
    @Override
    public List<Object[]> sqlArrayList(String sql) {
        Query query = em.createNativeQuery(sql);
        List<Object[]> list = query.getResultList();
        em.close();
        return list;
    }
*/


}
