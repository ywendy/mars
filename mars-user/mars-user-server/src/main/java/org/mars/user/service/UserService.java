package org.mars.user.service;

import org.mars.common.util.GenderEnum;
import org.mars.user.domain.User;
import org.mars.user.mapper.UserMapper;
import org.mars.user.vo.SimpleUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tony
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
                .nickName(user.getNickName()).build();
    }


    public SimpleUserVO getByUid(Long uid){
        if (uid ==null){
            return SimpleUserVO.builder().build();
        }
        User user = userMapper.selectByUid(uid);
        return generateSimpleVO(user);
    }


    public User getByLoginName(String loginName){
        return userMapper.selectByLoginName(loginName);
    }


    public User getUserDetailByUid(Long uid){
        return userMapper.selectByUid(uid);
    }



    private SimpleUserVO generateSimpleVO(User user){
        if (user == null){
            return SimpleUserVO.builder().build();
        }
        return SimpleUserVO.builder().age(user.getAge())
                .gender(GenderEnum.getValue(user.getGender()))
                .nickName(user.getNickName())
                .avatar(user.getAvatar())
                .uid(user.getUid())
                .build();
    }



}
