package com.example.major1.Config;

import com.example.major1.Services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    @Autowired
    GoogleOAuth2SuccessHandlder googleOAuth2SuccessHandlder;
    @Autowired
    CustomUserDetailService customUserDetailService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

       http.authorizeHttpRequests()
               .antMatchers("/", "/shop/**" , "/forgotpassword" ,"/register ","/mySQL-console/**" ).permitAll()
               .antMatchers("/admin/**").hasRole("ADMIN") .antMatchers("/shop/**").hasRole("USER")
               .anyRequest()
               .authenticated()
               .and()
               .formLogin()
               .loginPage("/login")
               .failureUrl("/login?error=true")
               .defaultSuccessUrl("/")
               .usernameParameter("email")
               .passwordParameter("password")
               .and()
               .oauth2Login()
               .loginPage("/login").permitAll()
               .successHandler(googleOAuth2SuccessHandlder)
               .and()
               .logout()
               .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
               .logoutSuccessUrl("/logout").permitAll()
               .invalidateHttpSession(true)
               .deleteCookies("JSESSIONID")
               .and()
               .exceptionHandling()
               .and()
               .csrf()
               .disable();

       http.headers().frameOptions().disable();

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
         final String[] IGNORE_PATTERNS = new String[] { "/resources/**","/static/**","productImages/**",   "/css/**" , "/js/**" };
        web.ignoring().antMatchers(IGNORE_PATTERNS);

    }
}
