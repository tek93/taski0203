package com.example.reqru2.config;

import com.example.reqru2.service.UserDetailsServiceImpl;
import com.example.reqru2.jwt.JwtAuthorizationFilter;
import com.example.reqru2.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Cross-origin-resource-sharing: localhost:8080, localhost:4200, 3000(allow for it.)
        http.cors().and()
                .authorizeRequests()
                //These are public pages.

                .antMatchers("/resources/**", "/error", "/api/user/**", "/h2-console/**").permitAll()
                .antMatchers("/api/localization/**").permitAll()






                .antMatchers("/api/admin/**").hasRole("ADMIN")

                //All reamining paths should need authentication.
                .anyRequest().fullyAuthenticated()
                .and()
                //Logout will log the user out by invalidated session.
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/user/logout", "POST"))
                .and()
                //login form and path.
                .formLogin().loginPage("/api/user/login").and()
                //Enable basic authentication http header "basic: username:password"
                .httpBasic().and()
                //Cross side request forgery.
                .csrf().disable();

        //jwt filter
        http.addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtTokenProvider));
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }
}
