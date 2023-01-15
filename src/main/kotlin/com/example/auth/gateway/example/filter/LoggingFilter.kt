package com.example.auth.gateway.example.filter

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.system.measureTimeMillis


class LoggingFilter : Filter {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        val httpServletRequest = request as HttpServletRequest
        val httpServletResponse = response as HttpServletResponse

        println("[REQ] ${httpServletRequest.method}  ${httpServletRequest.servletPath}")

        val measureTimeMillis = measureTimeMillis {
            chain.doFilter(request, response)
        }

        println("[RES] ${httpServletResponse.status}, $measureTimeMillis ms")
    }

}