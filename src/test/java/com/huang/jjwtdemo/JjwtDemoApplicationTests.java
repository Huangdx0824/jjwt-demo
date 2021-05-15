package com.huang.jjwtdemo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class JjwtDemoApplicationTests {

    /**
     * 创建Token(失效时间)
     *
     * @Author Brian
     * @Date 2021-05-15
     */
    @Test
    public void textCreateTokenHasExp() {
        // 当前系统时间
        long now = System.currentTimeMillis();
        // 过期时间,1分钟
        long exp = now + 60 * 1000 * 10;
        // 创建jwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder()
                // 声明的标识{"jti":"8888"}
                .setId("8888")
                // 主体，用户{"sub":"Rose"}
                .setSubject("Rose")
                // 创建日期{"ita":""}
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "xxxx")
                // 设置过期时间
                .setExpiration(new Date(exp));

        // 获取jwt的token
        String token = jwtBuilder.compact();
        System.out.println(token);

        System.out.println("==========================");
        String[] split = token.split("\\.");
        System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
        System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
        // 无法解密
        System.out.println(Base64Codec.BASE64.decodeToString(split[2]));

    }


    /**
     * 解析token
     *
     * @Author Brian
     * @Date 2021-05-15
     */
    @Test
    public void testParseTokenExp() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODg4Iiwic3ViIjoiUm9zZSIsImlhdCI6MTYyMTA2OTcxMywiZXhwIjoxNjIxMDcwMzEzfQ.DA8fobJvYikVApL5dCiKDVQLY6mCFx68pe0SxotVhXU";

        //解析token 获取声明中的对象
        Claims claims = Jwts.parser()
                .setSigningKey("xxxx")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("id:" + claims.getId());
        System.out.println("subject:" + claims.getSubject());
        System.out.println("issuedAt:" + claims.getIssuedAt());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("签发时间" + simpleDateFormat.format(claims.getIssuedAt()));
        System.out.println("过期时间" + simpleDateFormat.format(claims.getExpiration()));
        System.out.println("当前时间" + simpleDateFormat.format(new Date()));

    }



    /**
     * 创建Token(自定义声明)
     *
     * @Author Brian
     * @Date 2021-05-15
     */
    @Test
    public void textCreateTokenByClaims() {
        // 当前系统时间
        long now = System.currentTimeMillis();
        // 过期时间,1分钟
        long exp = now + 60 * 1000 * 10;
        // 创建jwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder()
                // 声明的标识{"jti":"8888"}
                .setId("8888")
                // 主体，用户{"sub":"Rose"}
                .setSubject("Rose")
                // 创建日期{"ita":""}
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "xxxx")
                // 设置过期时间
                .setExpiration(new Date(exp))
                // 自定义声明
                .claim("roles","admin")
                .claim("logo","idea.jpg");

        // 获取jwt的token
        String token = jwtBuilder.compact();
        System.out.println(token);

        System.out.println("==========================");
        String[] split = token.split("\\.");
        System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
        System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
        // 无法解密
        System.out.println(Base64Codec.BASE64.decodeToString(split[2]));

    }


    /**
     * 解析token(自定义声明)
     *
     * @Author Brian
     * @Date 2021-05-15
     */
    @Test
    public void testParseTokenExpByClaims() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODg4Iiwic3ViIjoiUm9zZSIsImlhdCI6MTYyMTA3NjYzMiwiZXhwIjoxNjIxMDc3MjMyLCJyb2xlcyI6ImFkbWluIiwibG9nbyI6ImlkZWEuanBnIn0.B_nXjS-09PlMHsh9TN-s9pDugrOEs1fByJ3lYCLA7GI";

        //解析token 获取声明中的对象
        Claims claims = Jwts.parser()
                .setSigningKey("xxxx")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("id:" + claims.getId());
        System.out.println("subject:" + claims.getSubject());
        System.out.println("issuedAt:" + claims.getIssuedAt());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("签发时间" + simpleDateFormat.format(claims.getIssuedAt()));
        System.out.println("过期时间" + simpleDateFormat.format(claims.getExpiration()));
        System.out.println("当前时间" + simpleDateFormat.format(new Date()));

        System.out.println("roles:"+claims.get("roles"));
        System.out.println("logo:"+claims.get("logo"));
    }


}
