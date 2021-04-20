package com.vanadis.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @program: vaps
 * @description: oauth2授权服务端配置
 * @author: 遥远
 * @create: 2019-08-30 10:28
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 配置客户端
     *
     * @param clients
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        log.info("初始化客服端列表");
        //添加客户端信息
        clients.inMemory()
                // 使用in-memory存储客户端信息
                .withClient("client")
                // client_id
                .secret("$2a$10$dYRcFip80f0jIKGzRGulFelK12036xWQKgajanfxT65QB4htsEXNK")
                // client_secret
                .authorizedGrantTypes("authorization_code")
                .redirectUris("http://www.baidu.com", "http://127.0.0.1:8022/login", "http://127.0.0.1:8022/login/oauth2/code/vap", "http://192.168.199.180:8022/login", "http://192.168.199.180:8022/swagger-ui.html")
                .autoApprove("user_info")
                .scopes("user_info");
    }

    /**
     * 配置授权服务器的安全
     *
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        log.info("配置令牌端点(Token Endpoint)的安全约束");
        super.configure(oauthServer);
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        log.info("配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)");
        super.configure(endpoints);
    }
}
