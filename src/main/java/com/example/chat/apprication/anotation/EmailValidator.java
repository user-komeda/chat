package com.example.chat.apprication.anotation;

import java.util.Map;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * EmailValidator.
 */
@Component
@NoArgsConstructor
public class EmailValidator implements
    ConstraintValidator<Email, Map<String, String>> {

  @Override
  public boolean isValid(final Map<String, String> value,
      final ConstraintValidatorContext context) {
    final String email = value.get("email");
    return email.matches(
        "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
  }
}
