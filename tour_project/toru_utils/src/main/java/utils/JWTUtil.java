package utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class JWTUtil {
    //签发主体
    private final static String iss = "magic.h3c.com";
    //加密密钥
    private final static String secret = "9a96349e2345385785e804e0f4254dee";

    //获取一个默认token字符串
    public static String getToken() throws UnsupportedEncodingException {
        long currentTime = System.currentTimeMillis();
        Date iat = new Date(currentTime);
        Date exp = new Date(currentTime + 30 * 60 * 1000);
        JWTCreator.Builder builder = JWT.create()
                .withIssuer(iss)//设置签发主体
                .withIssuedAt(iat)//设置生成时间
                .withExpiresAt(exp);//设置过期时间
        return builder.sign(Algorithm.HMAC256(secret));
    }

    //获取指定过期时期的token
    public static String getToken(long expiryTime) throws UnsupportedEncodingException {
        long currentTime = System.currentTimeMillis();
        Date iat = new Date(currentTime);
        Date exp = new Date(currentTime + expiryTime);
        JWTCreator.Builder builder = JWT.create()
                .withIssuer(iss)//设置签发主体
                .withIssuedAt(iat)//设置生成时间
                .withExpiresAt(exp);//设置过期时间
        return builder.sign(Algorithm.HMAC256(secret));
    }

    //获取指定头部和过期时期的token
    public static String getToken(Map<String,Object> headerClaims,long expiryTime) throws UnsupportedEncodingException {
        long currentTime = System.currentTimeMillis();
        Date iat = new Date(currentTime);
        Date exp = new Date(currentTime + expiryTime);
        JWTCreator.Builder builder = JWT.create()
                .withHeader(headerClaims)//设置头部
                .withIssuer(iss)//设置签发主体
                .withIssuedAt(iat)//设置生成时间
                .withExpiresAt(exp);//设置过期时间
        return builder.sign(Algorithm.HMAC256(secret));
    }

    //获取指定过期时期和自定义载荷的token

    /**
     *
     * @param expiryTime 过期时间，毫秒
     * @param payloadClaims 传入的自定义键值对
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getToken(long expiryTime,Map<String,String> payloadClaims) throws UnsupportedEncodingException {
        long currentTime = System.currentTimeMillis();
        Date iat = new Date(currentTime);
        Date exp = new Date(currentTime + expiryTime);
        JWTCreator.Builder builder = JWT.create()
                .withIssuer(iss)//设置签发主体
                .withIssuedAt(iat)//设置生成时间
                .withExpiresAt(exp);//设置过期时间
        Set<Map.Entry<String, String>> entries = payloadClaims.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            builder.withClaim(entry.getKey(),entry.getValue());
        }
        return builder.sign(Algorithm.HMAC256(secret));
    }

    //解析字符串
    public static Map<String, Claim> verify(String token) throws UnsupportedEncodingException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).withIssuer(iss).build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        Map<String, Claim> claims = jwt.getClaims();
        return claims;
        //Map集合的值是Claim类型，若值是String则使用asString方法输出，日期类型使用asDate方法，以此类推
    }
}
