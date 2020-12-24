package by.liauko.siarhei.pn.config.security.filter

import by.liauko.siarhei.pn.config.security.AUTH_HEADER
import by.liauko.siarhei.pn.config.security.SIGN_IN_URL
import by.liauko.siarhei.pn.config.security.TOKEN_EXPIRATION_TIME
import by.liauko.siarhei.pn.config.security.TOKEN_PREFIX
import by.liauko.siarhei.pn.dto.Credential
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Filter which perform actual authentication and return JWT for authenticated user.
 *
 * @author Siarhei Liauko
 * @since 1.0.0
 */
class JWTAuthenticationFilter(
        private val authManager: AuthenticationManager,
        private val authSecret: String
) : UsernamePasswordAuthenticationFilter() {

    private val objectMapper = ObjectMapper()
            .registerModule(KotlinModule())
            .registerModule(JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    init {
        // Set URL path for which requests filter will be execute.
        setFilterProcessesUrl(SIGN_IN_URL)
    }

    /**
     * Performs user authentication.
     *
     * @param request - [HttpServletRequest] instance from which to extract parameters and perform the authentication.
     * @param response - [HttpServletResponse] instance, which may be needed if the implementation has to do a
     * redirect as part of a multi-stage authentication process (such as OpenID).
     *
     * @return the authenticated user token.
     *
     * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter.attemptAuthentication
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val accountDetails = objectMapper.readValue(request.inputStream, Credential::class.java)

        return authManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        accountDetails.username,
                        accountDetails.password
                )
        )
    }

    /**
     * Default behaviour for successful authentication.
     *
     * Creates JWT and sets it to response header.
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    override fun successfulAuthentication(request: HttpServletRequest,
                                          response: HttpServletResponse,
                                          chain: FilterChain,
                                          authResult: Authentication) {
        val username = (authResult.principal as User).username

        val token = JWT.create()
                .withSubject(username)
                .withExpiresAt(Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(authSecret))
        response.setHeader(AUTH_HEADER, "$TOKEN_PREFIX $token")
        response.contentType = MediaType.APPLICATION_JSON_VALUE
    }
}
