package com.example.auth.gateway.example.security

import com.example.auth.gateway.example.user.Role
import com.example.auth.gateway.example.user.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.security.core.authority.SimpleGrantedAuthority

internal class UserDetailsImplTest {

    @Test
    fun `getAuthorities_UserDetails from User And check Values`() {
        // given
        val user = User("koangho93@naver.com", "1234")
            .apply {
                this.role = Role.ADMIN
            }

        // when
        val userDetailsImpl = UserDetailsImpl(user)

        // then
        assertEquals(userDetailsImpl.authorities.first(), SimpleGrantedAuthority(user.role.name))
        assertEquals(userDetailsImpl.username, user.email)
        assertEquals(userDetailsImpl.password, user.password)
    }
}