package com.techno.technicaltestspringboot.util

import com.techno.technicaltestspringboot.repository.UserRepository
import com.techno.technicaltestspringboot.service.UserServiceImpl
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.apache.tomcat.util.net.openssl.ciphers.Authentication
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter

@Component
class JWTGenerator{
    companion object {
        private const val SECRET_KEY = "SUPER_SECRETE"
//        private var instance: JWTGenerator? = JWTGenerator()
    }

    val log = LoggerFactory.getLogger(this::class.java)

    fun createJWT(id : String, name: String, username: String, email: String, subject: String): String {
        val signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS256
        val nowMills: Long = System.currentTimeMillis()
        val now = Date(nowMills)

        val apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY)
        val signingKey = SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.jcaName)

        val builder: JwtBuilder = Jwts.builder().setId(id)
            .setIssuedAt(now)
            .setSubject(subject)
            .setIssuer("technocenter")
            .setAudience("technocenter")
            .claim("name", name)
            .claim("username", username)
            .claim("email", email)
            .signWith(signatureAlgorithm, signingKey)

        val expMills = nowMills + (5 * 24 * 60 * 60 * 1000) //set exp token 5 hari
        val exp = Date(expMills)
        log.info(exp.toString())
        builder.setExpiration(exp)
        return builder.compact()
    }

    fun decodeJWT(jwt: String): Claims{
        val claims: Claims = Jwts.parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
            .parseClaimsJws(jwt).body
        !claims.expiration.before(Date())

        log.info("ID : ${claims.id}")
        log.info("Issuer : ${claims.issuer}")
        log.info("Subject : ${claims.subject}")
        return claims
    }

    fun validateToken(jwt: String): Boolean {
        try {
            val claims: Claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).body
            !claims.expiration.before(Date(System.currentTimeMillis()))
            return true
        } catch (e: Exception) {
            return false
        }
    }


}