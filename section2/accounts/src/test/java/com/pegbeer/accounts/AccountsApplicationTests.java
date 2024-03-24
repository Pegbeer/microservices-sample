package com.pegbeer.accounts;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.pegbeer.accounts.dto.CustomerDto;
import com.pegbeer.accounts.exception.CustomerAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountsApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldReturnGreetings(){
		ResponseEntity<String> response = restTemplate.getForEntity("/accounts",String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("Hello world");
	}


	@Test
	@DirtiesContext
	void shouldCreateANewAccount() throws NullPointerException{
		CustomerDto newCustomer = new CustomerDto("John doe", "johndoe@mail.com","12345678");
		ResponseEntity<Void> response = restTemplate.postForEntity("/accounts",newCustomer,Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		CustomerDto newCustomerAttempt = new CustomerDto("John doe", "johndoe@mail.com","12345678");
		ResponseEntity<Void> attemptResponse = restTemplate.postForEntity("/accounts",newCustomerAttempt,Void.class);
		assertThat(attemptResponse.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
		assertThat(attemptResponse.getHeaders().get("message").get(0)).isEqualTo(CustomerAlreadyExistsException.message);
	}

}
