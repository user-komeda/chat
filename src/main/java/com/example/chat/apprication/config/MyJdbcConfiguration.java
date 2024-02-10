package com.example.chat.apprication.config;

import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

/**
 * MyJdbcConfiguration.
 */
@NoArgsConstructor
@Configuration
public class MyJdbcConfiguration extends AbstractJdbcConfiguration {

  @Override
  public JdbcCustomConversions jdbcCustomConversions() {
    return new JdbcCustomConversions(List.of(new ByteToBooleanConverter()));
  }
}
