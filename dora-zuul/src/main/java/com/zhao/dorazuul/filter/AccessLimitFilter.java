package com.zhao.dorazuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zhao.common.respvo.BaseResponse;
import com.zhao.dorazuul.config.CustomConfig;
import org.aspectj.util.SoftHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: AccessLimitFilter
 * @Author: zhaolianqi
 * @Date: 2021/8/25 14:35
 * @Version: v1.0
 */
@RefreshScope
@Component
public class AccessLimitFilter extends ZuulFilter {

    private final Map<Integer, SoftReference<RateLimiter>> limiterMap = new SoftHashMap<>();

    private Logger logger = LoggerFactory.getLogger(AccessLimitFilter.class);
    @Autowired
    private CustomConfig customConfig;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        if (!customConfig.getLimitUrls().isEmpty()){
            RequestContext requestContext = RequestContext.getCurrentContext();
            String uri = requestContext.getRequest().getRequestURI();
            Map<String, Integer> limits = customConfig.getLimitUrls();
            uri = uri.replaceAll("/+", "-");
            if (uri.startsWith("-"))
                uri = uri.replaceFirst("-", "");
            if (limits.keySet().contains(uri)){
                RateLimiter limiter = getLimiterByRate(limits.get(uri));
                if (limiter != null && !limiter.tryAcquire(1, 0, TimeUnit.SECONDS)){
                    requestContext.setSendZuulResponse(false);
                    requestContext.setResponseBody(BaseResponse.ERROR(HttpStatus.TOO_MANY_REQUESTS.value(),"访问受限").toString());
                    requestContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
                    requestContext.getResponse().setCharacterEncoding("UTF-8");
                    requestContext.getResponse().setHeader("Content-Type", "application/json");
                }
            }
        }
        return null;
    }

    private RateLimiter getLimiterByRate(Integer rate){
        SoftReference<RateLimiter> softReference = limiterMap.get(rate);
        if (softReference != null){
            RateLimiter limiter = softReference.get();
            if (limiter != null)
                return limiter;
        }

        synchronized (this){
            softReference = limiterMap.get(rate);
            if (softReference != null){
                RateLimiter limiter = softReference.get();
                if (limiter != null)
                    return limiter;
            }

            RateLimiter limiter = RateLimiter.create(rate);
            limiterMap.put(rate, new SoftReference<>(limiter));
            return limiter;
        }
    }
}
