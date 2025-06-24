package ru.kentagon.dm.models

import jakarta.persistence.*
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

@Entity
@Table(name = "users")
class User(
    @Id
    var id: UUID = UUID.randomUUID(),

    var name: String,

    var email: String,

    @Column(name = "password")
    var userPassword: String,

    @Enumerated(EnumType.STRING)
    var role: Role
) : UserDetails {
    override fun getAuthorities(): List<SimpleGrantedAuthority> {
        return listOf(SimpleGrantedAuthority("ROLE_${role.name}"))
    }

    override fun getPassword(): String {
        return userPassword
    }

    override fun getUsername(): String {
        return name
    }

}
