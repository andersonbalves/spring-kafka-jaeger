package br.com.baratella.spring.infra.kafka.consumer;

import br.com.baratella.spring.kafka.avro.User;
import br.com.baratella.spring.usecase.xpto.IXPTOUsecase;
import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Consumer Logger")
@RequiredArgsConstructor
@KafkaListener(
    topics = "users",
    groupId = "group_id_1",
    errorHandler = "customKafkaListenerErrorHandler")
public class Consumer {

  private final Tracer tracer;
  private final IXPTOUsecase xptoUsecase;

  @KafkaHandler(isDefault = true)
  public void consume(@SpanTag("payload") User record) throws InterruptedException {
    xptoUsecase.execute(record.getName());
    log.info(String.format("Consumed message -> %s", record));
  }
}