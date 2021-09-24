package com.zhao.commonservice.config;

import com.zhao.commonservice.interceptor.PermissionInterceptor;
import com.zhao.commonservice.resolver.CurrentUserResolver;
import com.zhao.commonservice.resolver.PartBodyResolver;
import com.zhao.doraclients.client.AuthServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CommonWebMvcConfig implements WebMvcConfigurer {

    @Value("${security:false}")
    private boolean security;
    @Autowired
    private AuthServiceClient authServiceClient;

    @Autowired
    private PermissionInterceptor permissionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (security)
            registry.addInterceptor(permissionInterceptor).addPathPatterns("/api/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CurrentUserResolver(authServiceClient));
        resolvers.add(new PartBodyResolver());
    }
}
