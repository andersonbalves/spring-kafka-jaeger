package br.com.baratella.spring.infra.rest;

import br.com.baratella.spring.infra.kafka.producer.Producer;
import br.com.baratella.spring.kafka.avro.User;
import brave.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.util.StringUtils;
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

  @PostMapping(value = "/publish")
  public void sendMessageToKafkaTopic(@SpanTag("name") @RequestParam("name") String name,
      @SpanTag("age") @RequestParam("age") Integer age) {
    log.info("Iniciando processamento com traceID = " + tracer.currentSpan().context().traceId());
    if (StringUtils.isEmpty(name)) {
      throw new IllegalArgumentException("Parâmetro \"name\" não pode ser vazio");
    }
    this.producer.sendMessage(new User(name, age));
  }
}