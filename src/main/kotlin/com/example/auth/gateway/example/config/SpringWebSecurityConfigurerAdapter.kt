package com.example.auth.gateway.example.config

import com.example.auth.gateway.example.filter.LoggingFilter
import com.example.auth.gateway.example.filter.RequestValidationFilter
import com.example.auth.gateway.example.filter.StaticKeyAuthenticationFilter
import com.example.auth.gateway.example.security.UserDetailsServiceImpl
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
class SpringWebSecurityConfigurerAdapter(
    private val userDetailsServiceImpl: UserDetailsServiceImpl
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsServiceImpl)
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
    }

    override fun configure(http: HttpSecurity) {
        http
            .addFilterBefore(
                LoggingFilter(),
                BasicAuthenticationFilter::class.java
            )
            .addFilterAfter(
                RequestValidationFilter(),
                BasicAuthenticationFilter::class.java
            )
            .addFilterAt(
                StaticKeyAuthenticationFilter(),
                BasicAuthenticationFilter::class.java
            )
    }

}