package com.example.auth.gateway.example.user

import javax.persistence.*

@Entity
@Table(name = "sabarada_user")
class User(
    @Id
    val email: String,
    var password: String
) {

    @Enumerated(EnumType.STRING)
    var role: Role = Role.USER

}