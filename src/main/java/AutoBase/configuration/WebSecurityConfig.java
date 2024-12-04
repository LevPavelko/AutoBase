package AutoBase.configuration;

import AutoBase.service.user_service.UserService;
import AutoBase.utils.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

@Override
protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();

    http.authorizeRequests().mvcMatchers("/g").access("hasAnyRole('ROLE_DISPATCHER')");
    http.authorizeRequests()
            .mvcMatchers("/loginPage").permitAll()
            .anyRequest().authenticated();


    http.formLogin()
            .loginProcessingUrl("/j_spring_security_check")
            .loginPage("/loginPage")
            .defaultSuccessUrl("/", true)
            .failureUrl("/login?error=true")
            .usernameParameter("email")
            .passwordParameter("password");


    http.logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/logoutSuccessful");
}


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}


