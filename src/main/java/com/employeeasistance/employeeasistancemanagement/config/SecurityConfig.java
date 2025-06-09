
package com.employeeasistance.employeeasistancemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
           .csrf(csrf -> csrf.disable())
           .authorizeHttpRequests(auth -> auth
           .requestMatchers("/**").permitAll()
            )
            .formLogin(form -> form
            .loginPage("/login")
            .loginProcessingUrl("/auth")
            .defaultSuccessUrl("/home")
            .failureUrl("/login?error=true")
            .permitAll()
            )
            .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)
            .permitAll()
            )
            .exceptionHandling(ex -> ex
            .accessDeniedPage("/access-denied")
            );
                
        return http.build();
    }
}
