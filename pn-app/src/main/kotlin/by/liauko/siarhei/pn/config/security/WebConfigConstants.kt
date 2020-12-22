package by.liauko.siarhei.pn.config.security


/**
 * Header name for JWT.
 */
const val AUTH_HEADER = "Authorization"

/**
 * Constant from which JWT starts.
 */
const val TOKEN_PREFIX = "Bearer"

/**
 * JWT life time in milliseconds.
 */
const val TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000

/**
 * Count of iterations for PBKDF2 password encoding.
 */
const val PBKDF2_ITERATIONS = 185000

/**
 * The desired bit-length of the derived key by PBKDF2 password encoder.
 */
const val PBKDF2_HASH_SIZE = 32 * 8

/**
 * URL path for user sign in action.
 */
const val SIGN_IN_URL = "/api/v1/user/signin"

/**
 * URL path for user sign up action.
 */
const val SIGN_UP_URL = "/api/v1/user/signup"
