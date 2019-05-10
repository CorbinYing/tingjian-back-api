package org.corbin.client.repository;

import org.corbin.common.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Corbin
 * @Date: 2018/12/4
 * @ClassName: UserInfoRepository
 * @Descripton:
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

    UserInfo findByUserAccount(String userAccount);

    UserInfo findByUserMail(String userMail);

    UserInfo findByUserId(Long userId);

}
