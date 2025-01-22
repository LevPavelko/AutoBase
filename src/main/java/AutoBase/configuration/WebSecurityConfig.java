package AutoBase.configuration;

import AutoBase.service.user_service.UserServiceImpl;
import AutoBase.utils.CustomAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServiceImpl userServiceImpl;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

@Override
protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();

    http.authorizeRequests().antMatchers("/css/**").permitAll();
    http.authorizeRequests().mvcMatchers("/orders").access("hasAnyRole('ROLE_DISPATCHER')");
    http.authorizeRequests().mvcMatchers("/drivers").access("hasAnyRole('ROLE_DISPATCHER')");
    http.authorizeRequests().mvcMatchers("/trips").access("hasAnyRole('ROLE_DISPATCHER')");
    http.authorizeRequests().mvcMatchers("/addDriver").access("hasAnyRole('ROLE_DISPATCHER')");
    http.authorizeRequests().mvcMatchers("/activeTrip").access("hasAnyRole('ROLE_DRIVER')");

    http.authorizeRequests()
            .mvcMatchers("/login").permitAll()
            .anyRequest().authenticated();


    http.formLogin()
            .loginProcessingUrl("/j_spring_security_check")
            .loginPage("/login")
            .defaultSuccessUrl("/home", true)
            .successHandler(customAuthenticationSuccessHandler)
            .failureUrl("/login?error=true")
            .usernameParameter("email")
            .passwordParameter("password");


    http.logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/logoutSuccessful");

    http.exceptionHandling()
            .accessDeniedPage("/403");
}


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl).passwordEncoder(passwordEncoder);
    }

}


