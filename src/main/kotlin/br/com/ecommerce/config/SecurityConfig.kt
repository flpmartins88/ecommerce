package br.com.ecommerce.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.SecurityFilterChain
import javax.servlet.Filter
import javax.servlet.http.HttpServletRequest

/**
 * Security config
 *
 * TODO: refac to avoid the deprecation
 */
@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.authorizeHttpRequests().anyRequest().permitAll()
    }

    override fun userDetailsService(): UserDetailsService {

        return super.userDetailsService()
    }

    //    @Bean
//    fun filter(): SecurityFilterChain {
//    }
//    // or
//    @Bean
//    fun ignoringCustomizer(): WebSecurityCustomizer? {
//        return WebSecurityCustomizer { web: WebSecurity -> web.ignoring().antMatchers("/ignore1", "/ignore2") }
//    }

}
