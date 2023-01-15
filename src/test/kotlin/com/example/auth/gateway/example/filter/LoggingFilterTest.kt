package com.example.auth.gateway.example.filter

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import javax.servlet.FilterChain

internal class LoggingFilterTest {

    @Test
    fun `logging_filter_show_REQ_and_RES_logging`() {
        // given
        val filter = LoggingFilter()

        val mockHttpServletRequest = MockHttpServletRequest("POST", "/example")
            .apply {
                servletPath = "/example"
            }
        val mockHttpServletResponse = MockHttpServletResponse()
        val mockFilterChain = mockk<FilterChain>()

        every { mockFilterChain.doFilter(mockHttpServletRequest, mockHttpServletResponse) } returns Unit

        // when
        filter.doFilter(mockHttpServletRequest, mockHttpServletResponse, mockFilterChain)

        verify(exactly = 1) { mockFilterChain.doFilter(mockHttpServletRequest, mockHttpServletResponse) }
    }
}