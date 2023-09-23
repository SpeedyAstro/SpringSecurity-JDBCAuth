package in.astro.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfigApp {
    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("SecurityConfigApp.configAuthentication");
        auth.jdbcAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enabled from user where username=?")
                .authoritiesByUsernameQuery("select username,authority from authorities where username=?");
        System.out.println(auth);
    }


    @Bean
    public SecurityFilterChain customizedFilterSecurity(HttpSecurity security) throws Exception {
        security.authorizeHttpRequests(
                request -> request.antMatchers("/api/").permitAll()
                        .antMatchers("/api/admin/").hasAnyRole("ADMIN","USER")
                        .antMatchers("/api/user/").hasRole("USER")
                        .anyRequest().authenticated()
        ).formLogin();
        return security.build();
    }
}
