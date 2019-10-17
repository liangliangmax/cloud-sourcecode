package com.liang.webflux;

import com.liang.webflux.controller.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.Topic;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRouter {


    @Bean
    public RouterFunction routeCity(UserHandler userhandler) {
        return RouterFunctions.nest(RequestPredicates.path("/user"),
                RouterFunctions.route(RequestPredicates.GET("/list"),
                        req-> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .body(BodyInserters.fromObject(userhandler.list()))
                )

        );
    }
}
