package com.frgz.todomvc.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@EnableAutoConfiguration
public class RedisConfig {

  @Bean
  public RedisTemplate<?, ?> redisTemplate(final RedisConnectionFactory connectionFactory) {
    final RedisTemplate<?, ?> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);
    return template;
  }

}
