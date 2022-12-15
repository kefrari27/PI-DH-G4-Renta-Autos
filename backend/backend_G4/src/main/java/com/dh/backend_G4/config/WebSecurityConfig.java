package com.dh.backend_G4.config;

import com.dh.backend_G4.filter.JwtRequestFilter;
import com.dh.backend_G4.service.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioDetailsService usuarioDetailService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/v1/autenticacion",
                                "/api/v1/productos/**",
                                "/api/v1/ciudades/**",
                                "/api/v1/categorias/**",
                                "/api/v1/usuarios/**",
                                "/api/v1/caracteristicas/**",
                                "/api/v1/imagenes/**",
                                "/api/v1/roles/**",
                                "/api/v1/politicas/**",
                                "/api/v1/mail/**").permitAll()
                    //.antMatchers("/api/v1/reservas").hasRole("admin")
                    .antMatchers("/api/v1/reservas/**").authenticated()
                    //.antMatchers(HttpMethod.POST, "/api/v1/productos/**").hasAnyRole("admin")
                    .anyRequest().authenticated()
                .and().cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(usuarioDetailService);
        //auth.userDetailsService(usuarioDetailService).passwordEncoder(passwordEncoder());
    }
}
