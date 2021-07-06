package br.com.baratella.spring.kafka;

import br.com.baratella.spring.kafka.avro.User;
import io.opentracing.Tracer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.annotation.ContinueSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog(topic = "Consumer Logger")
public class Consumer {

  @Autowired
  private Tracer tracer;

  @Value("${topic.name}")
  private String topicName;

  @ContinueSpan()
  @KafkaListener(topics = "users", groupId = "group_id_2")
  public void consume(@SpanTag("payload") ConsumerRecord<String, User> record) {
//    log.debug("tracer: "+tracer);
//    log.info("active span: "+tracer.activeSpan());
    log.info(String.format("Consumed message -> %s", record.value()));
  }
}