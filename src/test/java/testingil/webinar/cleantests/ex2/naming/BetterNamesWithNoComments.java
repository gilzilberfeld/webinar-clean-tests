package testingil.webinar.cleantests.ex2.naming;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import testingil.webinar.cleantests.CalculatorParams;

class BetterNamesWithNoComments {

	private CalculatorParams calcParams;
	private HttpHeaders headers;
	private RestTemplate restTemplate;

	@BeforeEach
	public void setup() {
		calcParams = new CalculatorParams();
		headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    restTemplate = new RestTemplate();
	}
	
	@Test
	void add_two_numbers_and_calculate_result() throws JsonProcessingException {
		calcParams.addFirst(3);
		calcParams.addSecond(4);
		
	    HttpEntity<String> request = 
			      new HttpEntity<String>(calcParams.toJson(), headers);

	    String result = restTemplate.postForObject("http://localhost:8888/root/calculate", 
				request, String.class);

		assertThat(result, is("7"));
	}

	@Test
	void add_two_negative_numbers_and_calculate_result() throws Exception {
		calcParams.addFirst(-5);
		calcParams.addSecond(-4);
		
	    HttpEntity<String> request = 
			      new HttpEntity<String>(calcParams.toJson(), headers);

	    String result = restTemplate.postForObject("http://localhost:8888/root/calculate", 
				request, String.class);

		assertThat(result, is("-9"));
		
	}

}
