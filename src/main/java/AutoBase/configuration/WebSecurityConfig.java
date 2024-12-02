package AutoBase.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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


    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/login", "/logout").permitAll()  // Разрешаем доступ к страницам логина и логаута
                .mvcMatchers("/").authenticated()  // Главная страница теперь доступна только для аутентифицированных пользователей
                .anyRequest().authenticated()  // Для всех других запросов требуется аутентификация
                .and()
                .formLogin()
                .loginPage("/loginPage")  // Указываем страницу для логина
                .loginProcessingUrl("/login")  // URL для обработки данных формы логина
                .defaultSuccessUrl("/", true)  // После успешного входа перенаправляем на главную страницу
                .permitAll()  // Разрешаем доступ к странице входа без аутентификации
                .and()
                .logout()
                .permitAll();  // Разрешаем доступ к логауту без аутентификации
    }
}


