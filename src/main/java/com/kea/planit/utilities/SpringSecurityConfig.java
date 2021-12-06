package com.kea.planit.utilities;


import com.kea.planit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {



    protected void configure(final AuthenticationManagerBuilder auth, User user) throws Exception{
        auth.inMemoryAuthentication().
                withUser(user.getEmail()).password(passwordEncoder().encode(user.getPassword())).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/","/sign-up","/login").permitAll()
                .antMatchers("/account/**").access("hasRole('USER')")
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/view-tasks")
                        .failureUrl("/login?error=true")
                );
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
