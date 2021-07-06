package br.com.baratella.spring.rest;

import br.com.baratella.spring.kafka.Producer;
import br.com.baratella.spring.kafka.avro.User;
import io.opentracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class KafkaController {

  private final Producer producer;

  @Autowired
  private Tracer tracer;

  @Autowired
  KafkaController(Producer producer) {
    this.producer = producer;
  }

  @NewSpan("mySpan")
  @PostMapping(value = "/publish")
  public void sendMessageToKafkaTopic(@SpanTag("name")@RequestParam("name") String name, @SpanTag("age") @RequestParam("age") Integer age) {
//    log.debug("tracer: "+tracer);
//    log.info("active span: "+tracer.activeSpan());
    this.producer.sendMessage(new User(name, age));
  }
}