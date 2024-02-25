package com.example.application.config;

import com.example.application.filter.CheckTokenFilter;
import com.example.application.filter.ExceptionHandlerFilter;
import com.example.application.filter.UsernamePasswordAuthenticationFilterImpl;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive;
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

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    final HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(
        new ClearSiteDataHeaderWriter(
            Directive.ALL));
    http.authorizeRequests()
        .mvcMatchers("/signin", "/signup", "/verify/{verificationCode}", "/websocket", "/{id}")
        .permitAll().anyRequest().authenticated();
    http
        .logout((logout) -> logout.addLogoutHandler(clearSiteData).logoutUrl("/logout")
            .logoutSuccessHandler((request, response, authentication) -> {
              response.setStatus(HttpServletResponse.SC_OK);
            })
            .clearAuthentication(true).permitAll());
    http.addFilterBefore(new ExceptionHandlerFilter(),
        UsernamePasswordAuthenticationFilterImpl.class);
    http.addFilter(new UsernamePasswordAuthenticationFilterImpl(authenticationManager(),
        getApplicationContext()));
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
    configuration.setAllowedOriginPatterns(List.of("http://localhost:5173"));

    // Access-Control-Allow-Methods
    configuration.setAllowedMethods(List.of("*"));

    // Access-Control-Allow-Headers
    configuration.setAllowedHeaders(List.of("*"));

    // Access-Control-Allow-Credentials
    configuration.setAllowCredentials(Boolean.TRUE);

    final var source = new UrlBasedCorsConfigurationSource();

    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}
