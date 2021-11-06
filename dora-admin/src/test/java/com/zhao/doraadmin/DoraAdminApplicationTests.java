package com.zhao.doraadmin;

import com.zhao.commonservice.service.RedisService;
import org.junit.jupiter.api.Test;
import org.redisson.api.GeoUnit;
import org.redisson.api.RGeo;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class DoraAdminApplicationTests {

    @Autowired
    private RedisService redisService;
    private Logger logger = LoggerFactory.getLogger(DoraAdminApplicationTests.class);

    @Test
    void contextLoads() {

        RedissonClient client = redisService.getRedissonClient();
        RGeo<String> geo = client.getGeo("test-geo2");

//        geo.add(116.418139,39.914382, "王府井");
//        geo.add(116.407934,39.914105, "天安门东");
//        geo.add(116.442429,39.914603, "建国门");
//        geo.add(116.457089,39.914216, "永安里");
//        geo.add(116.380913,39.895563, "菜市口");
//        geo.add(116.364384,39.913219, "复兴门");
//        geo.add(116.3646,39.895563, "广安门内");

        List<String> res = geo.radius(116.448968,39.915267, 1500., GeoUnit.METERS);
        logger.info("{}", res);

        Double dist = geo.dist("永安里", "复兴门", GeoUnit.KILOMETERS);
        logger.info("{}", dist);

        Map<String, Double> distMap = geo.radiusWithDistance(116.380684,39.906177, 3, GeoUnit.KILOMETERS);
        logger.info("{}", distMap); // {菜市口=1.1809, 天安门东=2.4864, 广安门内=1.8105, 复兴门=1.596}

    }

}
