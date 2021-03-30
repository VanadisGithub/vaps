package tool.jwt;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * JwtHelper
 *
 * @author yaoyuan
 * @date 2020/12/6 11:38 上午
 */
public class JwtHelper {

    private static String JWT_SECRET = "dataphin";

    private static Integer SESSION_TIME = 30;

    private static String USER_ID = "USER_ID";

    private static String USER_NAME = "USER_NAME";

    private static String TENANT_ID = "TENANT_ID";

    public static void main(String[] args) throws Exception {
        String dpnToken = encryption(123456L, "dataphin_dev", 10001L, new Date());
        System.out.println(dpnToken);
        System.out.println(decryption(dpnToken));
    }

    public static String encryption(Long userId, String username, Long tenantId, Date expireAt) throws Exception {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(tenantId);
        try {
            //Token的过期时间和Session的过期时间保持一致
            Calendar c = Calendar.getInstance();
            c.setTime(expireAt);
            c.add(Calendar.MINUTE, SESSION_TIME);

            //create token builder
            JWTCreator.Builder builder = JWT.create()
                .withExpiresAt(c.getTime())
                .withIssuedAt(new Date())
                .withClaim(USER_ID, String.valueOf(userId))
                .withClaim(USER_NAME, username)
                .withClaim(TENANT_ID, String.valueOf(userId));

            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return builder.sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            throw new Exception("encryption error");
        }
    }

    public static DpnToken decryption(String tokenText) throws Exception {
        try {
            //验证
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(JWT_SECRET))
                .build()
                .verify(tokenText);

            DpnToken dpnToken = new DpnToken();
            dpnToken.setUserId(Long.parseLong(jwt.getClaim(USER_ID).asString()));
            dpnToken.setUserName(jwt.getClaim(USER_NAME).asString());
            dpnToken.setTenantId(Long.parseLong(jwt.getClaim(TENANT_ID).asString()));
            return dpnToken;
        } catch (UnsupportedEncodingException e) {
            throw new Exception("dpn_token解码异常.");
        } catch (TokenExpiredException e) {
            throw new Exception("dpn_token已过期.");
        }
    }

    public static class DpnToken {

        private Long userId;

        private String userName;

        private Long tenantId;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Long getTenantId() {
            return tenantId;
        }

        public void setTenantId(Long tenantId) {
            this.tenantId = tenantId;
        }

        @Override
        public String toString() {
            return "DpnToken{" +
                "userId=" + userId +
                ", userName=" + userName +
                ", tenantId=" + tenantId +
                '}';
        }
    }
}
