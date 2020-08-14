package ru.shcheglov.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN")
                .antMatchers("/profile/**").authenticated()
                .antMatchers("/login/**").permitAll()
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID");
    }

    @Bean
    public JdbcUserDetailsManager users(DataSource dataSource) {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}")
                .roles("ADMIN", "USER")
                .build();
        UserDetails superAdmin = User.builder()
                .username("superadmin")
                .password("{bcrypt}")
                .roles("ADMIN", "USER", "SUPERADMIN")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        if (!users.userExists(user.getUsername())) {
            users.createUser(user);
        }
        if (!users.userExists(admin.getUsername())) {
            users.createUser(admin);
        }
        if (!users.userExists(superAdmin.getUsername())) {
            users.createUser(superAdmin);
        }
        return users;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
