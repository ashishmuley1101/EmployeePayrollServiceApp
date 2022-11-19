package com.bridgelabz.employeepayrollappnew.config;

import com.bridgelabz.employeepayrollappnew.filter.JwtFilter;
import com.bridgelabz.employeepayrollappnew.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EmployeePayrollService userEmployeePayrollService;

    @Autowired
    private JwtFilter jwtFilter;

    /**
     * AuthenticationManagerBuilder use for getting userDetailsService by passing the userEmployeePayrollService as argument
     * which we fetch the details of user i.e. user name and gender
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userEmployeePayrollService);
    }


    // configure(HttpSecurity http)  it allows configuring web based security for specific http requests.
    //.antMatchers("/authenticate") is allows for particular URL. and .permitAll() permit all.
    //sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) creating the stateless Policy for client and server
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }


    // Spring Security’s servlet support storing passwords securely by integrating with PasswordEncoder.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    //AuthenticationManager for creating bean object.
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
