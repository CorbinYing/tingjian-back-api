package org.corbin.common.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author yin
 * @date 2019/06/17
 */
@Configuration
/**
 * 由于basedao是动态注入，
 * 所以需要指定子类repository的位置
 */
@EnableJpaRepositories(basePackages = {"org.corbin.common.repository"},
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class JpaBaseConfig {
}
