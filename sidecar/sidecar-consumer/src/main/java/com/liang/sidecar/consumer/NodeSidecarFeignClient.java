package com.liang.sidecar.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("sidecar-server")
public interface NodeSidecarFeignClient {

    @RequestMapping("/string")
    String hello();

}
