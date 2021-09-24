package com.zhao.common.utils;

import com.zhao.common.modal.TokenModel;
import com.zhao.common.modal.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 */
public class JwtTokenUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static String JWT_SECRET = null;
    private static Integer expiration = null;
    private static Integer refreshExp = 604800;

    public static boolean ready = false;

    public static TokenConfig getConfiguration(){
        TokenConfig config = new TokenConfig();
        config.setExpiration(expiration);
        config.setRefreshExp(refreshExp);
        config.setSecret(JWT_SECRET);
        return config;
    }

    public static void initConfig(String secret, int exp){
        initConfig(secret, exp, null);
    }

    public static void initConfig(TokenConfig config){
        initConfig(config.getSecret(), config.getExpiration(), config.getRefreshExp());
    }

    public static void initConfig(String secret, int exp, Integer refreshExp){
        if (refreshExp == null || refreshExp <= 0)
            refreshExp = 604800;
        if (exp <= 0)
            exp = 1800;

        if (JwtTokenUtil.JWT_SECRET == null){
            synchronized (JwtTokenUtil.class){
                if (JwtTokenUtil.JWT_SECRET == null){
                    JwtTokenUtil.JWT_SECRET = secret;
                    JwtTokenUtil.expiration = exp;
                    JwtTokenUtil.refreshExp = refreshExp;

                    JwtTokenUtil.ready = true;
                }
            }
        }
    }

    /**
     * 生成用户token
     * @param user
     * @return
     */
    public static String generateToken(UserInfo user){
        if (user == null)
            throw new RuntimeException("用户不存在");
        Map<String, Object> claims = new HashMap<>(4);
        claims.put("id", user.getId());
        claims.put("exp", generateExpirationDate().getTime());
        return generateToken(claims);
    }

    public static String generateRefreshToken(UserInfo user){
        if (user == null)
            throw new RuntimeException("用户不存在");
        Map<String, Object> claims = new HashMap<>(4);
        claims.put("id", user.getId());
        claims.put("sign", user.getSign());
        claims.put("exp", System.currentTimeMillis() + refreshExp * 1000);
        return generateToken(claims);
    }

    /**
     * 根据负责生成JWT的token
     */
    private static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString().replaceAll("-", ""))
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, generalKey())
                .compact();
    }

    // 由字符串生成加密key
    private static SecretKey generalKey() {
        // 本地的密码解码
        byte[] encodedKey = JWT_SECRET.getBytes(); // Base64.decodeBase64(Base64.encodeBase64String(JWT_SECRET.getBytes()));
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 从token中获取JWT中的负载
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(generalKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.info("token验证失败:{}", token);
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 判断token是否已经失效
     */
    public static boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        if (expiredDate == null)
            return true;
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    public static Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if (claims == null)
            return null;
        return claims.getExpiration();
    }

    public static TokenModel token2tokenModal(String token){
        if (StringUtils.isEmpty(token))
            return null;
        Claims claims = getClaimsFromToken(token);
        if (claims == null)
            return null;
        UserInfo user = new UserInfo() {
            @Override
            public Long getId() {
                Object idObj = claims.get("id");
                if (idObj != null)
                    return Long.parseLong(idObj.toString());
                return null;
            }
            @Override
            public String getSign() {
                Object sign = claims.get("sign");
                if (sign != null)
                    return (String) sign;
                return null;
            }
        };
        TokenModel tokenModel = new TokenModel();
        tokenModel.setUser(user);
        return tokenModel;
    }

}
