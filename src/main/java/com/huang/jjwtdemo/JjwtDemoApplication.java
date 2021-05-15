package com.huang.jjwtdemo;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class JjwtDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JjwtDemoApplication.class, args);

        System.out.println("==========================");
            // 创建jwtBuilder
            JwtBuilder jwtBuilder = Jwts.builder()
                    // 声明的标识{"jti":"8888"}
                    .setId("8888")
                    // 主体，用户{"sub":"Rose"}
                    .setSubject("Rose")
                    // 创建日期{"ita":""}
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256,"xxxx");

            // 获取jwt的token
            String token = jwtBuilder.compact();
            System.out.println(token);

            System.out.println("==========================");
            String[] split = token.split("\\.");
            System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
            System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
            System.out.println(Base64Codec.BASE64.decodeToString(split[2]));


    }

}
