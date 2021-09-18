package com.zhao.dorazuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zhao.common.respvo.BaseResponse;
import com.zhao.commonservice.utils.CommonUtils;
import com.zhao.dorazuul.config.CustomConfig;
import com.zhao.dorazuul.config.RateLimiterManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName: AccessLimitFilter
 * @Author: zhaolianqi
 * @Date: 2021/8/25 14:35
 * @Version: v1.0
 */
@RefreshScope
@Component
public class AccessLimitFilter extends ZuulFilter {

    @Autowired
    private CustomConfig customConfig;
    private final String DEFAULT_IDENTIFY = "default_identify";

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
            if (limits.containsKey(uri)){

                String identify = CommonUtils.getIpAddr(requestContext.getRequest());
                if (StringUtils.isEmpty(identify))
                    identify = DEFAULT_IDENTIFY;

                if (!RateLimiterManager.tryAcquireOnePermit(identify, limits.get(uri))){
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

}
