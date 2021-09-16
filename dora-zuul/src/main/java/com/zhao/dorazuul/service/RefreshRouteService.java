package com.zhao.dorazuul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @ClassName: RefreshRouteService
 * @Author: zhaolianqi
 * @Date: 2021/9/16 10:33
 * @Version: v1.0
 */
@Service
public class RefreshRouteService {

    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private RouteLocator routeLocator;

    public void refreshRoute() {
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
        publisher.publishEvent(routesRefreshedEvent);
    }

}
