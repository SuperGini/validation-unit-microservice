package com.gini.validationunit.config.security;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt.token.validation")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private String uri;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .oauth2ResourceServer(
                        jwt -> jwt.jwt()
                                .jwkSetUri(uri)
                                .jwtAuthenticationConverter(jwtConverter())
                );
        http
                .authorizeRequests()
                .mvcMatchers("/v1/parts/**").hasAnyRole("MANAGER", "USER")
                .mvcMatchers("/v1/x/**").hasAnyRole("MANAGER")
                .mvcMatchers("/v1/users").hasAnyRole("MANAGER")
                .anyRequest()
                .authenticated();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Bean
    public JwtAuthenticationConverter jwtConverter() {
        var converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(
                jwt -> {
                    Map<String, List<String>> keycloakRealmAccess = (Map<String, List<String>>) jwt.getClaims().get("realm_access");
                    return keycloakRealmAccess.get("roles").stream()
                            .map(roleName -> "ROLE_" + roleName)
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
                }
        );
        return converter;
    }
}
