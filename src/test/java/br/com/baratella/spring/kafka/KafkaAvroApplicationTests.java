package br.com.baratella.spring.kafka;

import br.com.baratella.spring.SpringAvroApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest( classes = SpringAvroApplication.class)
@EnableAutoConfiguration
public class KafkaAvroApplicationTests {

	@Test
	public void contextLoads() {
	}

}
