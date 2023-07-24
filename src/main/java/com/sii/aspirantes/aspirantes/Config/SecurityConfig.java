package com.sii.aspirantes.aspirantes.Config;

import com.sii.aspirantes.aspirantes.Filter.JwtFilter;
import com.sii.aspirantes.aspirantes.Service.CustomUserDetailsService;
import com.sii.aspirantes.aspirantes.Utils.JWTUtil;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private JWTUtil jwtUtil;

    private static final String[] SWAGGER = {
            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/configuration/**",
            "/swagger/resources/**"
    };

    private static final String[] WHITE_LIST = {
            "/api/acceso",
            "/api/aspirante/acceso",
            "/api/aspirante/registrar",
            "/api/estudiantes/**"
    };

    private static final String[] DDA_AUTH_REQ = {
            "/api/dda/evaluacion/**",
            "/api/dda/preguntas/**",
            "/api/dda/avisos/**",
            "/api/dda/notificaciones/**",
            "/api/acceso/cambiar_contrasena",
            "/api/dda/solicitudes/**",
            "/api/dda/aulas_examen/**",
            "/api/dda/evaluacion/**",
            "/api/dda/cursos/**",
            "/api/entidades_federativas/**",
            "/api/grupos/**",
            "/api/dda/instructores/**",
            "/api/dda/notificaciones/**",
            "/api/preguntas/**",
            "/api/preparatorias_procedencia/**"
    };

    private static final String[] ASP_AUTH_REQ = {
            "/api/entidades_federativas/**",
            "/api/preguntas/encuesta/**",
            "/api/preparatorias_procedencia/**",
            "/api/aspirante/protected/**"
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                //.antMatchers(DDA_AUTH_REQ).hasRole("DDA")
                //.antMatchers(ASP_AUTH_REQ).hasRole("ASPIRANTE")
                .antMatchers(DDA_AUTH_REQ).permitAll()
                .antMatchers(ASP_AUTH_REQ).permitAll()
                .antMatchers("api/sys_admin/**").hasRole("ADMIN")
//                .antMatchers("api/dda/evaluacion/por_alumnos").hasRole("ESTUDIANTE")
                .antMatchers("api/dda/evaluacion/por_alumnos").permitAll()
                .antMatchers("/escolares/**").permitAll()
                .antMatchers(WHITE_LIST).permitAll()
                .antMatchers(SWAGGER).permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;
    }
}
