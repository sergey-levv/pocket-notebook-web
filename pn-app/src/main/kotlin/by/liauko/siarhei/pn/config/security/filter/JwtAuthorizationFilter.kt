package by.liauko.siarhei.pn.config.security.filter

import by.liauko.siarhei.pn.config.security.AUTH_HEADER
import by.liauko.siarhei.pn.config.security.JWT_TOKEN_PREFIX
import by.liauko.siarhei.pn.service.UserService
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtAuthorizationFilter(authManager: AuthenticationManager,
                             private val userService: UserService) : BasicAuthenticationFilter(authManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader(AUTH_HEADER)

        if (header == null || !header.startsWith("$JWT_TOKEN_PREFIX ")) {
            // TODO: Throw exception
        }

        val auth = getAuthentication(header)
        SecurityContextHolder.getContext().authentication = auth
        chain.doFilter(request, response)

    }

    private fun getAuthentication(header: String): UsernamePasswordAuthenticationToken? {
        val token = header.substring(JWT_TOKEN_PREFIX.length + 1)

        try {
            val username = JWT.require(Algorithm.HMAC512("secret"))
                    .build()
                    .verify(token)
                    .subject ?: return null

            return UsernamePasswordAuthenticationToken(userService.getCredentials(username), null, emptyList())
        } catch (e: JWTVerificationException) {
            return null
        }
    }

}