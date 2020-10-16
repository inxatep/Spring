package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import web.config.handler.MyLoginSuccessHandler;
import web.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsServiceImp; // сервис, с помощью которого тащим пользователя
    private final MyLoginSuccessHandler loginSuccessHandler; // класс, в котором описана логика перенаправления пользователей по ролям

    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImp,
                          MyLoginSuccessHandler loginSuccessHandler) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImp); // конфигурация для прохождения аутентификации
    }
    /*
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImp).passwordEncoder(passwordEncoder()); // конфигурация для прохождения аутентификации
    }

     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //- попробуйте выяснить сами, что это даёт
        http.authorizeRequests()
                .antMatchers("/").permitAll() // доступность всем
                .antMatchers("/user/**").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')"); // разрешаем входить на /user пользователям с ролью User
               // .and().formLogin()  // Spring сам подставит свою логин форму
                //.successHandler(loginSuccessHandler); // подключаем наш SuccessHandler для перенеправления по ролям
        http.formLogin()
                // указываем страницу с формой логина
                .loginPage("/login")
                //указываем логику обработки при логине
                .successHandler(loginSuccessHandler)
                // указываем action с формы логина
                .loginProcessingUrl("/login")
                // Указываем параметры логина и пароля с формы логина
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                // даем доступ к форме логина всем
                .permitAll();
        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();
        http
                // делаем страницу регистрации недоступной для авторизированных пользователей
                .authorizeRequests()
                //страницы аутентификаци доступна всем
                .antMatchers("/login").anonymous()
                // защищенные URL
                .antMatchers("/admin/**").access("hasRole('ADMIN')").anyRequest().authenticated();
    }
    @Bean
    public static PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder(10);

    }
}

