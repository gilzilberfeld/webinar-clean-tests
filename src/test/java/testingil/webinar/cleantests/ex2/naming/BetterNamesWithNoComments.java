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
import com.fasterxml.jackson.databind.ObjectMapper;

import testingil.webinar.cleantests.CalculatorParams;
import testingil.webinar.cleantests.Ops;

class BetterNamesWithNoComments {

	private CalculatorParams calcParams;
	private HttpHeaders headers;
	private RestTemplate restTemplate;
	private ObjectMapper mapper;

	@BeforeEach
	public void setup() {
		calcParams = new CalculatorParams();
		headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    restTemplate = new RestTemplate();
	    mapper = new ObjectMapper();
	}
	
	@Test
	void add_two_numbers_and_calculate_result() throws JsonProcessingException {
		calcParams.setFirst(3);
		calcParams.setSecond(4);
	    calcParams.setOp(Ops.Plus);
		
	    String json = mapper.writeValueAsString(calcParams);
	    HttpEntity<String> request = 
			      new HttpEntity<String>(json, headers);

	    String result = restTemplate.postForObject("http://localhost:8888/root/calculate", 
				request, String.class);

		assertThat(result, is("7"));
	}

	@Test
	void add_two_negative_numbers_and_calculate_result() throws Exception {
		calcParams.setFirst(-5);
		calcParams.setSecond(-4);
	    calcParams.setOp(Ops.Plus);
		
	    String json = mapper.writeValueAsString(calcParams);
	    HttpEntity<String> request = 
			      new HttpEntity<String>(json, headers);

	    String result = restTemplate.postForObject("http://localhost:8888/root/calculate", 
				request, String.class);

		assertThat(result, is("-9"));
		
	}

	@Test
	void subtract_two_numbers_and_calculate_result() throws Exception {
		calcParams.setFirst(20);
		calcParams.setSecond(4);
		calcParams.setOp(Ops.Minus);

	    String json = mapper.writeValueAsString(calcParams);
	    HttpEntity<String> request = 
			      new HttpEntity<String>(json, headers);

	    String result = restTemplate.postForObject("http://localhost:8888/root/calculate", 
				request, String.class);

		assertThat(result, is("16"));
	}
	
}
