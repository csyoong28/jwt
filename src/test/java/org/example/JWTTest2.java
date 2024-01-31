package org.example;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;
import cn.hutool.crypto.asymmetric.RSA;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JWTTest2 {
    /*@Test
    public void testGenerateToken(){
        RSA rsa = new RSA(RSA_PRIVATE_KEY, null);
    }*/

    @Test
    public void testResolveToken(){
        // 创建解析对象，使用的算法和secret要与创建token时保持一致
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!34ADAS")).build();
        // 解析指定的token
        DecodedJWT decodedJWT = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6ImJhb2JhbyIsImV4cCI6MTcwMDQwNTE4MywidXNlcklkIjoyMX0.BSF76wHlUNSp-7SSaYmc5_ckjEReQQEDb1B9o59PuiI");
        // 获取解析后的token中的payload信息
        Claim userId = decodedJWT.getClaim("userId");
        Claim userName = decodedJWT.getClaim("userName");
        System.out.println(userId.asInt());
        System.out.println(userName.asString());
        // 输出超时时间
        System.out.println(decodedJWT.getExpiresAt());
    }

    @Test
    public void testUsingJwtUtils(){
        Map<String,String> map = new HashMap<>();
        map.put("userId", "21");
        map.put("userName", "baobao");
        String token = JWTUtils.getToken(map);

        DecodedJWT decodedJWT = JWTUtils.decode(token);
        Claim userId = decodedJWT.getClaim("userId");
        Claim userName = decodedJWT.getClaim("userName");
        System.out.println(userId.asString());
        System.out.println(userName.asString());
    }

}
