import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JWTTest {

    @Test
    public void test() throws UnsupportedEncodingException {
        //头部信息
        Map<String,Object> headerClaims = new HashMap<String,Object>();
        headerClaims.put("typ","JWT");
        headerClaims.put("alg","HS256");
        //签发主体
        String iss = "magic.h3c.com";
        //签发时间
        long currentTime = System.currentTimeMillis();
        Date iat = new Date(currentTime);
        //过期时间
        Date exp = new Date(currentTime + 30 * 60 * 1000);
        //加密密钥
        String secret = "9a96349e2345385785e804e0f4254dee";


        JWTCreator.Builder builder = JWT.create()
                .withHeader(headerClaims)//设置头部，有默认配置，可以不设置
                .withIssuer(iss)//设置签发主体
                .withIssuedAt(iat)//设置生成时间
                .withExpiresAt(exp);//设置过期时间
        //设置自定义参数
        builder.withClaim("uname","system").withClaim("role","ROOT");
        String tokenJson = builder.sign(Algorithm.HMAC256(secret));
        System.out.println(tokenJson);
    }

    @Test
    public void verify() throws UnsupportedEncodingException {
        String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiUk9PVCIsInVuYW1lIjoic3lzdGVtIiwiaXNzIjoibWFnaWMuaDNjLmNvbSIsImV4cCI6MTU4NDYwOTUzNSwiaWF0IjoxNTg0NjA3NzM1fQ.7MOx45ZfDMkmO1Ca64_h9xL18qzfXuBWN0Bk7d1v4lQ";
        String secret = "9a96349e2345385785e804e0f4254dee";
        String iss = "magic.h3c.com";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).withIssuer(iss).build();

        DecodedJWT jwt = jwtVerifier.verify(token);

        //获取载荷的数据
        Map<String, Claim> claims = jwt.getClaims();
        System.out.println(claims.get("uname").asString());
        System.out.println(claims.get("role").asString());

    }
}
