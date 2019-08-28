package org.corbin.common.base.service;

import org.corbin.common.base.dao.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * jpa baseService ，It is based on jpa 2.1.5.RELEASE
 * all service class can extends this base-service to use base-function
 *
 * @author yin
 * @date 2019/06/15
 */
@Service
public abstract class BaseService<T, ID extends Serializable> {
    /**
     * 根据bean的类型，动态注入
     */
    @Autowired
    private BaseRepository<T, ID> baseRepository;

    /**
     * select a union domain by PK
     * if not found ,would return null
     *
     * @param id must not be {@literal null}
     * @return Retrieves an entity by its id.
     */
    public T findById(ID id) {
        Optional<T> optional = baseRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * select a union domain by PK
     * if not found ,would return null
     *
     * @param id id is the pk
     * @return
     */
    public T findByPK(ID id) {
        return findById(id);
    }

    /**
     * find a union a intact domain by a un-intact domain,
     * if not find ,will return null
     *
     * @param domain
     * @return
     */
    public T findOne(T domain) {
        Assert.notNull(domain, "domain will be change type to Example ,it not be null");
        Optional<T> optional = baseRepository.findOne(Example.of(domain));
        return optional.orElse(null);
    }

    /**
     * this founction always return instance like hibernate used get   ,if not fount will be throws an exception ,
     * if you want to use 'null' to replace this exception ,please use 'findOne ' function
     * <p>
     * Returns a reference to the entity with the given identifier. Depending on how the JPA persistence provider is
     * implemented this is very likely to always return an instance and throw an
     * {@link EntityNotFoundException} on first access. Some of them will reject invalid identifiers
     * immediately.
     *
     * @param id must not be {@literal null}.
     * @return a reference to the entity with the given identifier.
     * @see EntityManager#getReference(Class, Object) for details on when an exception is thrown.
     */
    public T getOne(ID id) {
        Assert.notNull(id, "id must not be null");
        return baseRepository.getOne(id);
    }

    /**
     * Returns a single entity matching the given {@link Example} or {@literal null} if none was found.
     *
     * @param example must not be {@literal null}.
     * @return a single entity matching the given {@link Example} or {@link Optional#empty()} if none was found.
     * @throws IncorrectResultSizeDataAccessException if the Example yields more than one result.
     */
    public Optional<T> findOne(Example<T> example) {
        return baseRepository.findOne(example);
    }

    /**
     * find all domain elements
     *
     * @return
     */
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    /**
     * find all domain elements with sort
     * * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Sort)
     *
     * @return
     */
    public List<T> findAll(Sort sort) {
        return baseRepository.findAll(sort);
    }

    /**
     * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
     *
     * @param pageable
     * @return a page of entities
     */
    public Page<T> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.data.repository.query.QueryByExampleExecutor#findAll(org.springframework.data.domain.Example, org.springframework.data.domain.Sort)
     */
    <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return baseRepository.findAll(example, sort);
    }

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.data.repository.query.QueryByExampleExecutor#findAll(org.springframework.data.domain.Example)
     */
    <S extends T> List<S> findAll(Example<S> example) {
        return baseRepository.findAll(example);
    }


    /**
     * Returns a {@link Page} of entities matching the given {@link Example}. In case no match could be found, an empty
     * {@link Page} is returned.
     *
     * @param example  must not be {@literal null}.
     * @param pageable can be {@literal null}.
     * @return a {@link Page} of entities matching the given {@link Example}.
     */
    public Page<T> findAll(Example<T> example, Pageable pageable) {
        return baseRepository.findAll(example, pageable);
    }

    /**
     * returns a list domains which found by given ids
     *
     * @param ids
     * @return
     */
    public List<T> findAllById(Iterable<ID> ids) {
        return baseRepository.findAllById(ids);
    }

    /**
     * Returns the number of instances matching the given {@link Example}.
     *
     * @param example the {@link Example} to count instances for. Must not be {@literal null}.
     * @return the number of instances matching the {@link Example}.
     */
    public long count(Example<T> example) {
        Assert.notNull(example, "example must not be null");
        return baseRepository.count(example);
    }

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    public void deleteById(ID id) {
        baseRepository.deleteById(id);
    }

    /**
     * Deletes a given entity.
     *
     * @param entity
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    public void delete(T entity) {
        baseRepository.delete(entity);
    }

    /**
     * Deletes the given entities.
     *
     * @param entities
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    public void deleteAll(Iterable<? extends T> entities) {
        baseRepository.deleteAll(entities);
    }

    /**
     * Deletes all entities managed by the repository.
     */
    public void deleteAll() {
        baseRepository.deleteAll();
    }


    /**
     * Deletes the given entities in a batch . Assume that we will clear
     * the {@link EntityManager} after the call.
     *
     * @param entities
     */
    public void deleteInBatch(Iterable<T> entities) {
        baseRepository.deleteInBatch(entities);

    }

    /**
     * Deletes all entities in a batch call.
     */
    public void deleteAllInBatch() {
        baseRepository.deleteAllInBatch();
    }

    /**
     * save a instance
     *
     * @param domain
     * @return
     */
    public T save(T domain) {
        return baseRepository.saveAndFlush(domain);
    }

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.data.repository.CrudRepository
     */
    <S extends T> List<S> saveAll(Iterable<S> entities) {
        return baseRepository.saveAll(entities);
    }


    /**
     * return a union domain which found by hql
     *
     * @param hql
     * @return
     */
    public T queryOneByHql(String hql) {
        return baseRepository.queryOneByHql(hql);
    }

    /**
     * find list result with hql
     *
     * @param hql
     * @return
     */
    public List<T> queryArrayListByHql(String hql) {
        return baseRepository.queryArrayListByHql(hql);
    }



    public <T> List<T> getContent(Page<T> page){
        return page.getContent();
    }

    public <T> Page<T> pageImpl(List<T> list, Pageable pageable){
        Page<T> page= new PageImpl<T>(list,pageable,list.size());
        return page;
    }

}
