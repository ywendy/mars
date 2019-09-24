package org.mars.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.mars.user.domain.User;
import org.mars.user.service.UserService;
import org.mars.user.vo.SimpleUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tony
 * @date 2019/8/28
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/simple/{uid}")
    public SimpleUserVO simpleUserDetail(@PathVariable("uid") Long uid) {
        return userService.getByUid(uid);
    }



    @GetMapping("/user/{uid}")
    public User userDetail(@PathVariable("uid") Long uid){
        return userService.getUserDetailByUid(uid);
    }



}
