package org.corbin.common.repository;

import org.corbin.common.base.dao.BaseRepository;
import org.corbin.common.entity.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * @Author: Corbin
 * @Date: 2018/12/4
 * @ClassName: UserInfoRepository
 * @Descripton:
 */
@Repository
public interface UserInfoRepository extends BaseRepository<UserInfo,Long> {

    UserInfo findByUserAccount(String userAccount);

    UserInfo findByUserMail(String userMail);

    UserInfo findByUserId(Long userId);

}
