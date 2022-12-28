package com.example.auth.gateway.example.config

import com.example.auth.gateway.example.security.UserDetailsServiceImpl
import com.example.auth.gateway.example.user.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun ignoringCustomizer(): WebSecurityCustomizer? {
        return WebSecurityCustomizer { web: WebSecurity ->
            web
                .ignoring()
                .antMatchers("/h2-console/**")
        }
    }

    @Bean
    fun userDetailsService(
        userRepository: UserRepository
    ): UserDetailsService {
        return UserDetailsServiceImpl(userRepository)
    }

}