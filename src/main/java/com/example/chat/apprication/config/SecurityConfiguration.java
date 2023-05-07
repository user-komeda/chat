package com.example.chat.apprication.config;

import com.example.chat.apprication.filter.CheckTokenFilter;
import com.example.chat.apprication.filter.UsernamePasswordAuthenticationFilterImpl;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * springSecurityConfig.
 */
@Configuration
@EnableWebSecurity
@NoArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  /**
   * password encoder.
   *
   * @return PasswordEncoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests().mvcMatchers("/signin", "/signup", "/verify/{verificationCode}")
        .permitAll().anyRequest()
        .authenticated();
    http.addFilter(new UsernamePasswordAuthenticationFilterImpl(authenticationManager()));
    http.addFilterAfter(new CheckTokenFilter(), UsernamePasswordAuthenticationFilterImpl.class);
    http.csrf().disable();
    http.cors();
  }


  /**
   * Cors設定.
   *
   * @return CorsConfig
   */
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final var configuration = new CorsConfiguration();

    // Access-Control-Allow-Origin
    configuration.setAllowedOriginPatterns(List.of("*"));

    // Access-Control-Allow-Methods
    configuration.setAllowedMethods(List.of("*"));

    // Access-Control-Allow-Headers
    configuration.setAllowedHeaders(List.of("*"));

    // Access-Control-Allow-Credentials
    configuration.setAllowCredentials(true);

    final var source = new UrlBasedCorsConfigurationSource();

    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}
