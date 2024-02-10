package com.example.chat.apprication.config;

import com.example.chat.domain.object.PrincipalUser;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import lombok.Data;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

/**
 * UserInterceptor.
 */
@Data
public class UserInterceptor implements ChannelInterceptor {

  @Override
  public Message<?> preSend(final Message<?> message, final MessageChannel channel) {
    final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message,
        StompHeaderAccessor.class);
    if (StompCommand.CONNECT == Objects.requireNonNull(accessor).getCommand()) {
      final Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);

      final Object name = ((Map<?, ?>) Objects.requireNonNull(raw)).get("userName");
      accessor.setUser(new PrincipalUser(((ArrayList<String>) name).get(0)));
    }
    return message;
  }
}

