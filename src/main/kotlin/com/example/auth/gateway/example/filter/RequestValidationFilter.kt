package com.example.auth.gateway.example.filter

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RequestValidationFilter : Filter {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        val httpRequest: HttpServletRequest = request as HttpServletRequest
        val httpResponse: HttpServletResponse = response as HttpServletResponse

        val requestId = httpRequest.getHeader("Request-Id")

        if (requestId == null || requestId.isBlank()) {
            httpResponse.status = HttpServletResponse.SC_BAD_REQUEST
            return
        }

        chain.doFilter(request, response)
    }
}