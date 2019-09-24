package org.mars.api.user.service;

import org.mars.user.vo.SimpleUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yaojian
 * @date 2019/8/28
 */
@FeignClient("mars-user-server")
public interface UserService {



    @GetMapping("/user/simple/{uid}")
    SimpleUserVO simpleUserDetail(@PathVariable("uid") Long uid);


}
