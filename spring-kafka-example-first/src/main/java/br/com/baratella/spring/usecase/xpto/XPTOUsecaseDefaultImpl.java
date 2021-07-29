package br.com.baratella.spring.usecase.xpto;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Service;

@Service
public class XPTOUsecaseDefaultImpl implements IXPTOUsecase {

  @NewSpan("sleepOuNao")
  public void execute(String name) throws InterruptedException {
    if ("sleep".equalsIgnoreCase(name)) {
      Thread.sleep(2000);
    }
    if ("exception".equalsIgnoreCase(name)) {
      throw new RuntimeException("Exceção lançada para teste");
    }
  }

}
