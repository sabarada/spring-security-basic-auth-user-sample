package com.example.auth.gateway.example.filter

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.system.measureTimeMillis


class StaticKeyAuthenticationFilter : Filter {

    private val authorizationKey = "sabarada"

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        val httpServletRequest = request as HttpServletRequest
        val httpServletResponse = response as HttpServletResponse

        val authentication = httpServletRequest.getHeader("Authorization")

        if (authentication == authorizationKey) {
            chain.doFilter(request, response)
        } else {
            httpServletResponse.status = HttpServletResponse.SC_BAD_REQUEST
        }
    }

}