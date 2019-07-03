package com.kdpark0723.communityCommon.model.user.dataTransferObject


import com.fasterxml.jackson.annotation.JsonIgnore
import com.kdpark0723.communityCommon.model.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors

class UserPrincipal(val id: Long?,
                    val name: String,
                    private val username: String,
                    @field:JsonIgnore val email: String,
                    @field:JsonIgnore private val password: String,
                    private val authorities: Collection<GrantedAuthority>) : UserDetails {

    override fun getUsername(): String {
        return username
    }

    override fun getPassword(): String {
        return password
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as UserPrincipal?
        return id == that!!.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }

    companion object {
        fun create(user: User): UserPrincipal {
            val authorities = user.roles.stream().map { role ->
                SimpleGrantedAuthority(role.name?.name)
            }.collect(Collectors.toList())

            return UserPrincipal(
                user.id,
                user.name,
                user.username,
                user.email,
                user.password,
                authorities
            )
        }
    }
}