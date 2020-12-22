package by.liauko.siarhei.pn.config.security.filter

import by.liauko.siarhei.pn.config.security.AUTH_HEADER
import by.liauko.siarhei.pn.config.security.TOKEN_PREFIX
import by.liauko.siarhei.pn.service.AccountService
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


/**
 * Filter which checks if JWT exists in request headers and sets up authenticated principal in [SecurityContextHolder]
 * or ignores failed authentication attempts, allowing the request to proceed down the filter chain.
 *
 * @see BasicAuthenticationFilter
 * @author Siarhei Liauko
 * @since 1.0.0
 */
class JWTAuthorizationFilter(
        authManager: AuthenticationManager,
        private val accountService: AccountService,
        private val authSecret: String
) : BasicAuthenticationFilter(authManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader(AUTH_HEADER)

        if (header == null || !header.startsWith("$TOKEN_PREFIX ")) {
            chain.doFilter(request, response)
            return
        }

        SecurityContextHolder.getContext().authentication = getAuthentication(header)
        chain.doFilter(request, response)

    }

    private fun getAuthentication(header: String): UsernamePasswordAuthenticationToken? {
        val token = header.substring(TOKEN_PREFIX.length + 1)

        try {
            val username = JWT.require(Algorithm.HMAC512(authSecret))
                    .build()
                    .verify(token)
                    .subject ?: return null

            return UsernamePasswordAuthenticationToken(accountService.getAccountDetails(username), null, emptyList())
        } catch (e: JWTVerificationException) {
            return null
        }
    }

}
