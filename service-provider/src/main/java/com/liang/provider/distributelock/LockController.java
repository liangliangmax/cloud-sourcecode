package com.liang.provider.distributelock;


import com.liang.api.entity.User;
import com.liang.provider.distributelock.annotation.CacheParam;
import com.liang.provider.distributelock.annotation.DistributeLock;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/lock")
public class LockController {

    private Map map = new ConcurrentHashMap();

    @PostMapping(value = "/user",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @DistributeLock(prefix = "user")
    public String insert(@CacheParam @RequestBody User user){

        map.put(user.getUsername(),user);

        return "保存成功";
    }

}
