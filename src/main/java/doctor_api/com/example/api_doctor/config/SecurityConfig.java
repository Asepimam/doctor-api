package doctor_api.com.example.api_doctor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import doctor_api.com.example.api_doctor.helper.JwtRequestFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) 
        .authorizeHttpRequests(requests -> requests
                .requestMatchers("/api/auth/login").permitAll() // Login tanpa autentikasi
                .anyRequest().authenticated() // Semua endpoint lain harus diautentikasi
        )
        .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless untuk JWT
        )
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Tambahkan filter JWT

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    };

}
