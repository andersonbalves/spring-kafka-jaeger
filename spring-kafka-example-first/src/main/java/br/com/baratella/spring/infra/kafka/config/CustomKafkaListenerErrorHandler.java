package br.com.baratella.spring.infra.kafka.config;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@CommonsLog(topic = "Error Handler Logger")

@Component
public class CustomKafkaListenerErrorHandler implements KafkaListenerErrorHandler {

  @Override
  public Object handleError(Message<?> message, ListenerExecutionFailedException e) {
    log.error(message, e);
    return null;
  }
}
