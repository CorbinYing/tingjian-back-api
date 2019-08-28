package org.corbin.common.base.config;

import org.corbin.common.base.dao.BaseRepositoryImpl;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author yin
 * @date 2019/06/15
 */
public class BaseRepositoryFactory<T, ID extends Serializable> extends JpaRepositoryFactory {
    private final EntityManager entityManager;

    /**
     * Creates a new {@link JpaRepositoryFactory}.
     *
     * @param entityManager must not be {@literal null}
     */
    public BaseRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    /**设置具体的实现类是BaseRepositoryImpl
     *
     * @param information
     * @param entityManager
     * @return
     */
    @Override
    protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
        return new BaseRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);
    }

    /**设置具体的实现类的class
     *
     * @param metadata
     * @return
     */
    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return BaseRepositoryImpl.class;
    }


}
