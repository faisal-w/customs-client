package com.equilibrium.kiriman.security;

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

import javax.sql.DataSource;

/**
 * Created by faisalw on 3/29/17.
 */
@Configuration
@EnableWebSecurity
public class CoreSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SimpleAuthSuccessHandler myAuthSuccessHandler;

    @Autowired
    DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery(
                        "select username,password, enabled from ask_treck.user where username=?")
                .authoritiesByUsernameQuery(
                        "select username, role from ask_treck.user where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .authorizeRequests()
            .antMatchers("/resources/**").permitAll()
            .antMatchers("/static/**").permitAll()
            .antMatchers("/console/**").permitAll()
            .antMatchers("/penerima/**",
                        "/pengirim/**",
                        "/bank/**",
                        "/deposit/**",
                        "/admin/**",
                        "/perusahaan/**",
                        "/user/**").hasRole("SUPERADMIN")
            .anyRequest().authenticated()
        .and()
            .headers().frameOptions().sameOrigin()
        .and()
            .formLogin().loginPage("/login").failureUrl("/error").permitAll()
            .usernameParameter("username").passwordParameter("password")
            .successHandler(myAuthSuccessHandler)
        .and()
            .exceptionHandling()
                .accessDeniedPage("/access-denied")
        .and()
            .csrf().disable();
            //.antMatchers("/analisis/perencanaan","/analisis/realisasi").access("hasRole('PEMERIKSA')")
//            .antMatchers("/add_client/").permitAll()
//            .antMatchers("/console/**").permitAll()
            /*
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login/logout")
            .and()
            .exceptionHandling().accessDeniedPage("/403")
            .and()
            .csrf();
            */
    }

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("superadmin").password("super admin ganteng").roles("SUPERADMIN")
            .and()
            .withUser("user").password("user").roles("USER");
    }*/

}
