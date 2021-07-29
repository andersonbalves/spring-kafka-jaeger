package br.com.baratella.spring.infra.kafka.config;

import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.util.concurrent.ListenableFutureCallback;

public abstract class CustomListenableFutureCallback<T> implements ListenableFutureCallback<T> {

  private final Tracer tracer;
  private final Span span;

  public CustomListenableFutureCallback(Tracer tracer) {
    this.tracer = tracer;
    this.span = tracer.activeSpan();
  }

}
