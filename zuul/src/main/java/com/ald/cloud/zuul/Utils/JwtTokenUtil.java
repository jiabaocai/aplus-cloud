package com.ald.cloud.zuul.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
  *                  ,___         .-;'
  *                  `"-.`\_...._/`.`
  *             ,       \        /
  *         .-' ',      / ()   ()\
  *         `'._ \     /()    .  (|
  *             > .'  ;,     -'-  /
  *            / <   |;,     __.;
  *            '-.'-.|  , \    , \
  *               `>.|;, \_)    \_)
  *                `-;     ,    /
  *                  \    /   <
  *                  '. <`'-, _)
  *                  '._)
  * 
  * @Author luohao
  * @Version 1.0.0
  * @Date 13:31 2017/12/5
  * @Desription 
  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
  */

public class JwtTokenUtil {

    private static Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

    /**
     * 生成一个token
     * @param claims, 需要放入token的参数
     * @param tokenSecret
     * @return
     */
    public static String generateToken(Map<String, Object> claims, String tokenSecret) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject("aladin")
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
    }

    /**
     * 获取
     * @param token
     * @return
     */
    public static Claims getToken(String token, String tokenSecret) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(tokenSecret)
                    .requireSubject("aladin")
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 173);
        map.put("roleType", 2);
        String tokenSecret = "0123456789";
        String token = generateToken(map, tokenSecret);
        System.out.println(token);

        System.out.println(getToken(token, tokenSecret));

    }


}