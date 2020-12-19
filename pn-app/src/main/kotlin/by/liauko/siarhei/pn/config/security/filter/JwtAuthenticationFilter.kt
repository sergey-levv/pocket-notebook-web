package by.liauko.siarhei.pn.config.security.filter

import by.liauko.siarhei.pn.config.security.AUTH_HEADER
import by.liauko.siarhei.pn.config.security.JWT_TOKEN_PREFIX
import by.liauko.siarhei.pn.config.security.TOKEN_EXPIRATION_TIME
import by.liauko.siarhei.pn.dto.User
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtAuthenticationFilter(private val authManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val credentials = ObjectMapper().readValue(request.inputStream, User::class.java)

        return authManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        credentials.username,
                        credentials.password
                )
        )
    }

    override fun successfulAuthentication(request: HttpServletRequest,
                                          response: HttpServletResponse,
                                          chain: FilterChain,
                                          authResult: Authentication) {
        val username = (authResult.principal as User).username

        val token = JWT.create()
                .withSubject(username)
                .withExpiresAt(Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .sign(Algorithm.HMAC512("secret"))
        response.setHeader(AUTH_HEADER, "$JWT_TOKEN_PREFIX $token")
        response.contentType = MediaType.APPLICATION_JSON_VALUE
    }
}