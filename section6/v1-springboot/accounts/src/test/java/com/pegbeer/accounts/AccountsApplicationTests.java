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
	@DirtiesContext
	void shouldCreateANewAccount() throws NullPointerException{
		CustomerDto newCustomer = new CustomerDto();
		newCustomer.setName("John Doe");
		newCustomer.setEmail("johndoe@mail.com");
		newCustomer.setMobileNumber("12345678");
		ResponseEntity<Void> response = restTemplate.postForEntity("/accounts",newCustomer,Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);


		ResponseEntity<Void> attemptResponse = restTemplate.postForEntity("/accounts",newCustomer,Void.class);
		assertThat(attemptResponse.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
		assertThat(attemptResponse.getHeaders().get("message").get(0)).isEqualTo(CustomerAlreadyExistsException.message);
	}

	@Test
	void shouldNotReturnACustomerWithAnUnknownMobileNumber(){
		ResponseEntity<String> response = restTemplate.getForEntity("/accounts/7986896", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}

	@Test
	@DirtiesContext
	void shouldReturnDataWhenIsSaved(){
		CustomerDto customer = new CustomerDto();
		customer.setName("John Doe");
		customer.setEmail("johndoe@mail.com");
		customer.setMobileNumber("12345678");
		ResponseEntity<Void> response = restTemplate.postForEntity("/accounts", customer, Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		ResponseEntity<String> customerResponse = restTemplate
				.getForEntity("/accounts/"+customer.getMobileNumber(), String.class);
		assertThat(customerResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext document = JsonPath.parse(customerResponse.getBody());
		String name = document.read("$.name");
		assertThat(name).isNotEmpty();

		String email = document.read("$.email");
		assertThat(email).isNotEmpty();
	}

	@Test
	void shouldReturnTheBuildVersionInfo(){
		ResponseEntity<String> response = restTemplate.getForEntity("/accounts/build-info", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		String buildVersion = response.getBody();
		assertThat(buildVersion).isNotEmpty();
		assertThat(buildVersion).isEqualTo("1.0");
	}

}
