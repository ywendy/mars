package org.mars.user.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mars.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author yaojian
 * @date 2019/8/30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectByKey(){
        User user = userMapper.selectByPrimaryKey(1L);
        System.out.println(user);
    }


}