package br.com.baratella.spring.infra.kafka.producer;

import br.com.baratella.spring.kafka.avro.User;
import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@CommonsLog(topic = "Producer Logger")
@RequiredArgsConstructor
public class Producer {

  private final KafkaTemplate<String, User> kafkaTemplate;
  private final Tracer tracer;
  @Value("${topic.name}")
  private String TOPIC;

  @NewSpan("ProducerKafka_2")
  public void sendMessage(@SpanTag("payload") User user) {
    ListenableFuture<SendResult<String, User>> future = this.kafkaTemplate.send(this.TOPIC, user);
    future.addCallback(new ListenableFutureCallback<>() {

      @Override
      @NewSpan("success")
      public void onSuccess(SendResult<String, User> result) {
        log.info(String.format("Produced user -> %s", result));
      }

      @Override
      @NewSpan("fail")
      public void onFailure(Throwable ex) {
        log.error(ex);
      }
    });
    log.info(String.format("Produced user -> %s", user));
  }
}
