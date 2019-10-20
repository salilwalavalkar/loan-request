package de.salil.homework.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class}) /* Disabled security */
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .pathMatchers(HttpMethod.POST, "/**").hasAuthority("SCOPE_write")
                .pathMatchers(HttpMethod.GET, "/**").hasAuthority("SCOPE_read")
                .pathMatchers(HttpMethod.PUT, "/**").hasAuthority("SCOPE_write")
                .pathMatchers(HttpMethod.DELETE, "/**").hasAuthority("SCOPE_write")
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt().jwkSetUri("http://localhost:9180/.well-known/jwks.json");
        return http.build();
    }
}
