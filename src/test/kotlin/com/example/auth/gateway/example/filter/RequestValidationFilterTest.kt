package com.example.auth.gateway.example.filter

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import javax.servlet.FilterChain

internal class RequestValidationFilterTest {

    @Test
    fun `RequestValidation_filter_when_header_is_null_then_400_return`() {
        // given
        val filter = RequestValidationFilter()

        val mockHttpServletRequest = MockHttpServletRequest("POST", "/example")
            .apply {
                servletPath = "/example"
            }
        val mockHttpServletResponse = MockHttpServletResponse()
        val mockFilterChain = mockk<FilterChain>()

        every { mockFilterChain.doFilter(mockHttpServletRequest, mockHttpServletResponse) } returns Unit

        // when
        filter.doFilter(mockHttpServletRequest, mockHttpServletResponse, mockFilterChain)

        assert(mockHttpServletResponse.status == 400)
        verify(exactly = 0) { mockFilterChain.doFilter(mockHttpServletRequest, mockHttpServletResponse) }
    }

    @Test
    fun `RequestValidation_filter_when_header_is_not_null_then_through_the_filter`() {
        // given
        val filter = RequestValidationFilter()

        val mockHttpServletRequest = MockHttpServletRequest("POST", "/example")
            .apply {
                servletPath = "/example"
                addHeader("Request-Id", "test")
            }
        val mockHttpServletResponse = MockHttpServletResponse()
        val mockFilterChain = mockk<FilterChain>()

        every { mockFilterChain.doFilter(mockHttpServletRequest, mockHttpServletResponse) } returns Unit

        // when
        filter.doFilter(mockHttpServletRequest, mockHttpServletResponse, mockFilterChain)

        verify(exactly = 1) { mockFilterChain.doFilter(mockHttpServletRequest, mockHttpServletResponse) }
    }

}