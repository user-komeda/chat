package com.example.chat.apprication.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Email.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {

  /**
   * バリデーションエラーメッセージ.
   *
   * @return message
   */
  String message() default "";

  /**
   * バリデーショングループ.
   *
   * @return group
   */
  Class<?>[] groups() default {};

  /**
   * payload.
   *
   * @return payload
   */
  Class<? extends Payload>[] payload() default {};


}