package com.example.auth.gateway.example.security

import com.example.auth.gateway.example.user.User
import com.example.auth.gateway.example.user.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserDetailsServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {

        if (username == null) {
            return throw NullPointerException("can't find this user. username is null")
        }

        val user: User = userRepository.findByEmail(username)
            ?: throw NullPointerException("can't find this user. username: $username")

        return UserDetailsImpl(
            user.apply {
                password = passwordEncoder.encode(this.password)
            }
        )
    }
}