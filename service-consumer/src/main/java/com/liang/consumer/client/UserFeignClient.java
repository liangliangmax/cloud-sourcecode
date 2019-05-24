package com.liang.consumer.client;

import com.liang.api.clientapi.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "service-provider")
public interface UserFeignClient extends UserApi {
}
