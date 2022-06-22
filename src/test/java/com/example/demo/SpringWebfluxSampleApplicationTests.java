package com.example.demo;

import com.example.demo.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class SpringWebfluxSampleApplicationTests {

	@LocalServerPort
	private int port;

	private static final String BASE_URL = "http://localhost:";

	@Test
	void contextLoads() throws InterruptedException {
		List<Customer> list = new ArrayList<>();
		Flux<Customer> customerFlux = WebClient.create().get()
														.uri(BASE_URL + port + "/customers/all/stream")
														.retrieve()
														.bodyToFlux(Customer.class);

		StepVerifier.create(customerFlux)
				.expectSubscription()
				.recordWith(ArrayList::new)
				.thenConsumeWhile(x -> true)
				.consumeRecordedWith(e -> log.info("{}", e))
				.verifyComplete();
	}

}
