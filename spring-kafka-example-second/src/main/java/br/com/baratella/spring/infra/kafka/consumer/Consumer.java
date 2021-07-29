package br.com.baratella.spring.infra.kafka.consumer;

import br.com.baratella.spring.kafka.avro.User;
import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Consumer Logger")
@RequiredArgsConstructor
@KafkaListener(
    topics = "users",
    groupId = "group_id_2",
    errorHandler = "customKafkaListenerErrorHandler")
public class Consumer {

  private final Tracer tracer;
  @Value("${topic.name}")
  private String topicName;

  @NewSpan("ConsumerKafka_2")
  @KafkaHandler(isDefault = true)
  public void consume(@SpanTag("payload") User record) {
    log.info(String.format("Consumed message -> %s", record));
  }
}