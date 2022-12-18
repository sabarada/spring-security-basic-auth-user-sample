package com.example.auth.gateway.example.config

import com.example.auth.gateway.example.security.UserDetailsServiceImpl
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@Configuration
class SpringWebSecurityConfigurerAdapter(
    private val userDetailsServiceImpl: UserDetailsServiceImpl
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsServiceImpl)
    }


}