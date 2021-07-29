package br.com.baratella.spring.infra.rest;

import br.com.baratella.spring.infra.kafka.producer.Producer;
import br.com.baratella.spring.kafka.avro.User;
import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@CommonsLog(topic = "Controller Logger")
@RequiredArgsConstructor
public class KafkaController {

  private final Producer producer;

  private final Tracer tracer;


  @NewSpan("ControllerKafka_2")
  @PostMapping(value = "/publish")
  public void sendMessageToKafkaTopic(@SpanTag("name") @RequestParam("name") String name,
      @SpanTag("age") @RequestParam("age") Integer age) {
    this.producer.sendMessage(new User(name, age));
  }
}