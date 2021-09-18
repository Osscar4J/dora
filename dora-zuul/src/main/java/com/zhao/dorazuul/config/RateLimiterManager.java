package com.zhao.dorazuul.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RateLimiterManager
 * @Author: zhaolianqi
 * @Date: 2021/9/18 11:28
 * @Version: v1.0
 */
public class RateLimiterManager {

    private static final Cache<String, RateLimiter> RATE_LIMITER_CACHE = CacheBuilder.newBuilder()
            .maximumSize(5000).expireAfterWrite(3, TimeUnit.DAYS).build();
    private static Logger logger = LoggerFactory.getLogger(RateLimiterManager.class);

    /**
     * 申请获取一个许可<p>
     *
     * 同一个 identify 共享一个限速器<p>
     *
     * @param identify 身份信息
     * @param permitsPerSnd 当前的限速值
     */
    public static void acquireOnePermit(final String identify, final double permitsPerSnd) {
        String newIdentify = identify + "-" + permitsPerSnd;
        try {
            RateLimiter rateLimiter = RATE_LIMITER_CACHE.get(newIdentify, new Callable<RateLimiter>() {
                /**
                 * Computes a result, or throws an exception if unable to do so.
                 *
                 * @return computed result
                 * @throws Exception if unable to compute a result
                 */
                @Override
                public RateLimiter call() throws Exception {
                    logger.info("已创建限流组件-[identify={}, permitsPerSnd={}]", newIdentify, permitsPerSnd);
                    return RateLimiter.create(permitsPerSnd);
                }
            });
            rateLimiter.acquire();
        } catch (Exception e) {
            logger.error("获取限流组件时异常,identify={},permitsPerSnd={}", newIdentify, permitsPerSnd);
            e.printStackTrace();
        }
    }

    /**
     * 如果获取到就返回true.否则返回false.
     * 这个是不阻塞当前线程的
     * @param identify
     * @param permitsPerSnd
     */
    public static boolean tryAcquireOnePermit(final String identify, final double permitsPerSnd) {
        String newIdentify = identify + "-" + permitsPerSnd;
        try {
            RateLimiter rateLimiter = RATE_LIMITER_CACHE.get(newIdentify, new Callable<RateLimiter>() {
                /**
                 * Computes a result, or throws an exception if unable to do so.
                 *
                 * @return computed result
                 * @throws Exception if unable to compute a result
                 */
                @Override
                public RateLimiter call() throws Exception {
                    logger.info("已创建限流组件-[identify={}, permitsPerSnd={}]", newIdentify, permitsPerSnd);
                    return RateLimiter.create(permitsPerSnd);
                }
            });
            return rateLimiter.tryAcquire();
        } catch (Exception e) {
            logger.error("获取限流组件时异常,identify={},permitsPerSnd={}", newIdentify, permitsPerSnd);
            e.printStackTrace();
            return false;
        }
    }
}
