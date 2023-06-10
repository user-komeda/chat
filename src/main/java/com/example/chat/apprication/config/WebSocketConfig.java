package com.example.chat.apprication.config;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocketConfig.
 */
@Configuration
@EnableWebSocketMessageBroker
@NoArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void registerStompEndpoints(final StompEndpointRegistry registry) {
    registry
        .addEndpoint("/websocket") // ①
        .setAllowedOriginPatterns("*"); // ③
  }

  @Override
  public void configureMessageBroker(final MessageBrokerRegistry config) {
    config.enableSimpleBroker("/topic", "/queue");
    config.setApplicationDestinationPrefixes("/channels");
    config.setUserDestinationPrefix("/users");
  }

  @Override
  public void configureClientInboundChannel(final ChannelRegistration registration) {
    registration.interceptors(new UserInterceptor());
  }
}
