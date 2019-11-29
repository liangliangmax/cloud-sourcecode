package com.liang.sidecar.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("/sidecar")
public class ConsumerController {

    @Autowired
    private DiscoveryClient discoveryClient;


    @Autowired
    private NodeSidecarFeignClient nodeSidecarFeignClient;

    @RequestMapping("/hello")
    public String test(){
        System.out.println(111);

        List<ServiceInstance> list = discoveryClient.getInstances("sidecar-server");

        list.stream().forEach((item)-> System.out.println(item.getUri()));

        return nodeSidecarFeignClient.hello();
    }
}
