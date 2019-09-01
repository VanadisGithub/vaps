package com.vanadis.vap.conf.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

/**
 * @description:
 * @author: Created by 遥远 on 2019-03-21 17:43
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(vapClientRegistration(), githubClientRegistration(), googleClientRegistration());
    }

    private ClientRegistration vapClientRegistration() {
        return ClientRegistration.withRegistrationId("vap")
                .clientId("client")
                .clientSecret("12345")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .tokenUri("http://localhost:9999/oauth/token")
                .authorizationUri("http://localhost:9999/oauth/authorize")
                .userInfoUri("http://localhost:9999/user/me")
                .redirectUriTemplate("{baseUrl}/{action}/oauth2/code/{registrationId}")
                .userNameAttributeName("name")
                .clientName("Vap")
                .build();
    }

    private ClientRegistration githubClientRegistration() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .clientId("5cc157387a790c5204e1")
                .clientSecret("d47ea7f5340fc33118f30f691666d9956ce2bcff")
                .build();
    }

    private ClientRegistration googleClientRegistration() {
        return CommonOAuth2Provider.GOOGLE.getBuilder("google")
                .clientId("594014553745-c32lehnngg6unckm5299h5hcet2lbfdo.apps.googleusercontent.com")
                .clientSecret("bhtfubUHtrquasO3oUjQultq")
                .build();
    }


}
