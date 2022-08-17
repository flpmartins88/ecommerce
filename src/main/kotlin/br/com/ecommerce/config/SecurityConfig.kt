package br.com.ecommerce.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun userDetailsService(): UserDetailsService {
        return InMemoryUserDetailsManager()
    }

    @Bean
    fun securityFilter(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity.authorizeRequests()
            .antMatchers("/").permitAll()
            .and()
            .build()
    }

    @Bean
    fun ignoringCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().antMatchers("/ignore1").antMatchers("/ignore2")
        }
    }

}
