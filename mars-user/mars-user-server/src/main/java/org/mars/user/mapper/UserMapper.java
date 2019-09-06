package org.mars.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mars.user.domain.User;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);
    User selectByUid(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}