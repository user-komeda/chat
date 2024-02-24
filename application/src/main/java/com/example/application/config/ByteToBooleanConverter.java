package com.example.application.config;

import java.util.Objects;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

/**
 * ByteToBooleanConverter.
 */
@WritingConverter
@NoArgsConstructor
public class ByteToBooleanConverter implements Converter<Byte, Boolean> {

  @Override
  public Boolean convert(final Byte source) {
    Objects.requireNonNull(source);

    return source == 1 ? Boolean.TRUE : Boolean.FALSE;
  }
}
