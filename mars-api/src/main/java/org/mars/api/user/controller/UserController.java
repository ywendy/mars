package org.mars.api.user.controller;

import org.mars.api.user.service.UserService;
import org.mars.common.result.Result;
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
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/simple/{uid}")
    public Result simpleUser(@PathVariable("uid") Long uid){
        return  Result.success(userService.simpleUserDetail(uid));
    }

}
