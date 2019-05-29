package com.liang.api.clientapi;

import com.liang.api.entity.NeuabcRestApiResult;
import com.liang.api.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
public interface UserApi {

    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    NeuabcRestApiResult<String> save(@RequestBody User user);

    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    NeuabcRestApiResult<String> add(@RequestBody User user);


    @GetMapping(value = "/getById",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public NeuabcRestApiResult<User> getById(@RequestParam("id") String id);
}
