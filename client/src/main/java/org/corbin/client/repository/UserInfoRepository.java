package org.corbin.client.repository;

import org.corbin.common.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Corbin
 * @Date: 2018/12/4
 * @ClassName: UserInfoRepository
 * @Descripton:
 */

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

    UserInfo findByUserAccount(String userAccount);

}
