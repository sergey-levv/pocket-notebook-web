package by.liauko.siarhei.pn.config.security

import by.liauko.siarhei.pn.config.security.filter.JWTAuthenticationFilter
import by.liauko.siarhei.pn.config.security.filter.JWTAuthorizationFilter
import by.liauko.siarhei.pn.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


/**
 * Contain application web configuration.
 *
 * @author Siarhei Liauko
 * @since 1.0.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig(
        @Value("\${pbkdf2.secret}")
        private val pbkdf2Secret: String,
        @Value("\${auth.secret}")
        private val authSecret: String
) : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var accountService: AccountService
    @Autowired
    private lateinit var passwordEncoder: Pbkdf2PasswordEncoder

    /**
     * Configure [HttpSecurity].
     *
     * This implementation enabling CORS filter, disabling CSRF support, specifying URL for which user
     * do not need to be authenticated, setting up filters which creates JWT and managing user authorization,
     * setting up session creation policy to Stateless status and setting up authentication entry point.
     *
     * @see HttpSecurity
     * @see SessionCreationPolicy.STATELESS
     * @see JWTAuthenticationFilter
     * @see JWTAuthorizationFilter
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_IN_URL, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(JWTAuthenticationFilter(authenticationManager(), authSecret))
                .addFilter(JWTAuthorizationFilter(authenticationManager(), accountService, authSecret))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling().authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
    }

    /**
     * Configure [org.springframework.security.authentication.AuthenticationManager].
     *
     * The implementation setting up user details service implementation and password encoder.
     *
     * @see AuthenticationManagerBuilder
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(accountService).passwordEncoder(passwordEncoder)
    }

    @Bean
    fun pbkdf2Encoder() = Pbkdf2PasswordEncoder(pbkdf2Secret, PBKDF2_ITERATIONS, PBKDF2_HASH_SIZE)

    @Bean
    fun corsConfigurationSource() = UrlBasedCorsConfigurationSource().apply {
        registerCorsConfiguration("/**", CorsConfiguration().applyPermitDefaultValues().apply {
            exposedHeaders = listOf(AUTH_HEADER)
        })
    }
}
