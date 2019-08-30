package org.mars.user.service;

import org.mars.user.domain.User;
import org.mars.user.mapper.UserMapper;
import org.mars.user.vo.SimpleUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yaojian
 * @date 2019/8/30
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public SimpleUserVO getById(Long id){
        User user = userMapper.selectByPrimaryKey(id);

        return SimpleUserVO.builder().age(user.getAge())
                .avatar(user.getAvatar())
                .gender(String.valueOf(user.getGender()))
                .id(user.getId())
                .nickName(user.getNickName()).build();
    }

}
