package br.com.baratella.spring.kafka;

import br.com.baratella.spring.SpringKafkaJaegerSecond;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest( classes = SpringKafkaJaegerSecond.class)
@EnableAutoConfiguration
public class KafkaAvroApplicationTests {

	@Test
	public void contextLoads() {
	}

}
