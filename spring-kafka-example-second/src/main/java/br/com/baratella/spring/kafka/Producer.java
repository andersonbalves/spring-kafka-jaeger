package br.com.baratella.spring.kafka;

import br.com.baratella.spring.kafka.avro.User;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.annotation.ContinueSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog(topic = "Producer Logger")
public class Producer {

  @Value("${topic.name}")
  private String TOPIC;

  private final KafkaTemplate<String, User> kafkaTemplate;

  @Autowired
  private Tracer tracer;

  @Autowired
  public Producer(KafkaTemplate<String, User> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @ContinueSpan()
  public void sendMessage(@SpanTag("payload") User user) {
//    log.debug("tracer: " + tracer);
//    log.info("active span: " + tracer.activeSpan());
    this.kafkaTemplate.send(this.TOPIC, user.getName(), user);
    log.info(String.format("Produced user -> %s", user));
  }
}
