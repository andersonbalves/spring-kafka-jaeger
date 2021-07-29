package br.com.baratella.spring.usecase.xpto;

import org.springframework.stereotype.Service;

@Service
public interface IXPTOUsecase {

  void execute(String name) throws InterruptedException;

}
