package com.nhanhuynh.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

/*    @Bean
    public AuthenticationSuccessHandler SimpleAuthenticationSuccessHandler(){
        return new SimpleUrlAuthenticationSuccessHandler();
    }
*/

/*    @Autowired
    private SimpleAuthenticationSuccessHandler successHandler;
*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf()
//                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/login"))
//                .and()
                .authorizeRequests()
//                .antMatchers("/")
//                .access("hasAnyRole('USER','ADMIN')")
                .antMatchers("/admin")
//                .hasAuthority("ADMIN")
//                .hasRole("ADMIN")
                .access("hasRole('ADMIN')")
                .antMatchers("/user")
//                .hasAuthority("USER")
//                .hasRole("USER")
                .access("hasAnyRole('USER','ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSuccessHandler())
                //.defaultSuccessUrl("/")
//                .permitAll()
//                .successHandler(successHandler)

                .and()
                .logout()
                .permitAll();

//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login").permitAll();
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("nhan").password(passwordEncoder().encode("awesome"))
                .roles("ADMIN")
//                .authorities("ADMIN")
                .and()
                .withUser("dave").password(passwordEncoder().encode("begreat"))
                .roles("USER")
//                .authorities("USER")
                .and()
                .withUser("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
//                .authorities("USER")
                .and()
                .passwordEncoder(passwordEncoder());
    }




}

