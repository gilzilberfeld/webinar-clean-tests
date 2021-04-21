package testingil.webinar.cleantests.ex0.start;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import testingil.webinar.cleantests.CalculatorParams;


class DirtyTests {

	// Check that we can add two numbers 
	// and return the right result as a string
	@Test
	void test_add() throws Exception {
		CalculatorParams calcParams =new CalculatorParams();
		calcParams.addFirst(3);
		calcParams.addSecond(4);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		
	    HttpEntity<String> request = 
			      new HttpEntity<String>(calcParams.toJson(), headers);
			    
		RestTemplate restTemplate  = new RestTemplate();
		String result = restTemplate.postForObject("http://localhost:8888/root/calculate", 
				request, String.class);

		assertThat(result, is("7"));
	}

	// Check that we can add two numbers 
	// and return the right result as a string
	@Test
	void test_add_minus() throws Exception {
		CalculatorParams calcParams =new CalculatorParams();
		calcParams.addFirst(-5);
		calcParams.addSecond(-4);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		
	    HttpEntity<String> request = 
			      new HttpEntity<String>(calcParams.toJson(), headers);
			    
		RestTemplate restTemplate  = new RestTemplate();
		String result = restTemplate.postForObject("http://localhost:8888/root/calculate", 
				request, String.class);

		assertThat(result, is("-9"));
		
	}

}
