package com.vanadis.lang.encryption;

import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JwtHelper
 *
 * @author yaoyuan
 * @date 2020/12/6 11:38 上午
 */
public class JwtUtils {

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    private static final String JWT_SECRET = "D@T@ph1n";

    public static String encryption(String name, String content, Integer seconds) {
        if (Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(content)) {
            return null;
        }
        //Token的过期时间和Session的过期时间保持一致
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, seconds);

        JWTCreator.Builder builder = JWT.create()
            .withExpiresAt(c.getTime())
            .withIssuedAt(new Date())
            .withClaim(name, content);

        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
        return builder.sign(algorithm);
    }

    public static String decryption(String name, String tokenText) throws Exception {
        if (Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(tokenText)) {
            return null;
        }
        try {
            //验证
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(JWT_SECRET)).build().verify(tokenText);

            return jwt.getClaim(name).asString();
        } catch (TokenExpiredException e) {
            log.error("{}已过期.{}", name, tokenText, e);
            throw new Exception("JWT已过期");
        } catch (Exception e) {
            log.error("{}解码异常.{}", name, tokenText, e);
            throw new Exception("JWT解码异常.");
        }
    }

    public static void main(String[] args) throws Exception {
        Integer sessionTime = 86400;
        String encryption = encryption("test", "test_content", sessionTime);
        System.out.println(encryption);
        encryption = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjQwMjU5ODcxMDA3ODAzNDQxNDUifQ.eyJlbWFpbCI6ImFkbWluQG1haWwuY29tIiwibmFtZSI6Ium7mOiupOeuoeeQhuWRmCIsIm1vYmlsZSI6bnVsbCwiZXh0ZXJuYWxJZCI6IjI0MTY4MDk1MzE5OTkwNzM0NCIsInVkQWNjb3VudFV1aWQiOiIzZjJiYWRhMmRmY2YwMDg1NDJiM2VlY2MwZGI5YTRlMzlsUlNYR0pZV09RIiwib3VJZCI6IjM3OTk1NjU5Mzk4NzE2NjY2OTEiLCJvdU5hbWUiOiLpn7Xovr4iLCJvcGVuSWQiOm51bGwsImlkcFVzZXJuYW1lIjoiYWRtaW4iLCJ1c2VybmFtZSI6ImFkbWluIiwiYXBwbGljYXRpb25OYW1lIjoiSldUX0RhdGFwaGluIiwiZW50ZXJwcmlzZUlkIjoidGVzdCIsImluc3RhbmNlSWQiOiJ0ZXN0IiwiYWxpeXVuRG9tYWluIjoiIiwiZXh0ZW5kRmllbGRzIjp7fSwiZXhwIjoxNjQyMTQyNzg5LCJqdGkiOiJaSU5tNVhDVzhpd0lzVUVXZmFTYTB3IiwiaWF0IjoxNjQyMTM5MTg5LCJuYmYiOjE2NDIxMzkxMjksInN1YiI6ImFkbWluIiwiaXNzIjoiaHR0cDovLzEyMS44OS4yNDMuNjcvIiwiYXVkIjoidGVzdHBsdWdpbl9qd3QyMCJ9.pimx049zvwKgDo61mnEVB_MGotXOAnDu08XPFDC7EOKLW0nQEDbEu0mdeMsohn_bUoUbFXSnYS1ToHCmcWOvGo0a2bjXP6PNWSR3W5rE5Eh3r9QugU9oJTprKyr1rZ8tGqdr170mwULzq7xkl3n77qmocMsy2gDR_-Z7oWNXV9AOPKR08QrVjT9XToN9HApe4rFyc6ympSY2FxBDGgmj9Xuau4S8Rhync52bacipsD7pdBMiMLRsbibLU_ccKu6VdMwvzae_hdxV72gMxs7HpW9tPX27yeJffI5ldj7VZmEp7ns65pUx1eeu3runol-T-MWrua2ZBrVPQmelffBHNw";
        System.out.println(decryption("test", encryption));
    }
}