package com.example.auth.gateway.example.security

import com.example.auth.gateway.example.user.User
import com.example.auth.gateway.example.user.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

import org.springframework.security.crypto.password.PasswordEncoder

internal class UserDetailsServiceImplTest {

    private val userRepository: UserRepository = mockk()

    private val passwordEncoder: PasswordEncoder = mockk()

    lateinit var mut: UserDetailsServiceImpl

    @BeforeEach
    fun setUp() {
        mut = UserDetailsServiceImpl(userRepository, passwordEncoder)
    }

    @Test
    fun `loadUserByUsername_if username is null then throw NPE`() {
        // given
        val username = null

        // when & then
        assertThrows<NullPointerException> {
            mut.loadUserByUsername(username)
        }
    }

    @Test
    fun `loadUserByUsername_if can't found user then throw NPE`() {
        // given
        val username = "koangho93@naver.com"

        every { userRepository.findByEmail(username) } returns null

        // when & then
        assertThrows<NullPointerException> {
            mut.loadUserByUsername(username)
        }
    }

    @Test
    fun `loadUserByUsername_if found user then returns UserDetails`() {
        // given
        val username = "koangho93@naver.com"
        val user = User(username, "1234")
        val encodedPassword = "4321"

        every { userRepository.findByEmail(username) } returns user
        every { passwordEncoder.encode(user.password) } returns encodedPassword

        // when
        val userDetails = mut.loadUserByUsername(username)

        // then
        assertEquals(userDetails.username, user.email)
        assertEquals(userDetails.password, encodedPassword)
        assertEquals(userDetails.authorities.first().authority, user.role.name)
    }
}