package by.liauko.siarhei.pn.service

import by.liauko.siarhei.pn.dto.User
import org.springframework.security.core.userdetails.UserDetailsService


interface UserService : UserDetailsService {

    fun getCredentials(username: String): User
}