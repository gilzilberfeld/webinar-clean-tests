package testingil.webinar.cleantests.ex5.abstraction;

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
import testingil.webinar.cleantests.Consts;
import testingil.webinar.cleantests.Ops;

class WithBuilder {

	private String URL = "http://localhost:8888";
	private CalculatorParams calcParams;
	private HttpHeaders headers;
	private RestTemplate restTemplate;
	private CalcParamBuilder paramBuilder;

	@BeforeEach
	public void setup() {
		URL += Consts.ROOT + Consts.CALCULATE;
		headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    restTemplate = new RestTemplate();
		paramBuilder = new CalcParamBuilder();

	}
	
	@Test
	void add_two_numbers_and_calculate_result() throws JsonProcessingException {
		calcParams = paramBuilder.withFirst(3).withSecond(4).build(); 
		
	    String result = callAdd(URL);
		assertThat(result, is("7"));
	}


	@Test
	void add_two_negative_numbers_and_calculate_result() throws Exception {
		calcParams = paramBuilder.withFirst(-5).withSecond(-4).build();
		
	    String result = callAdd(URL);
		assertThat(result, is("-9"));
		
	}
	
	@Test
	void subtract_two_numbers() throws Exception {
		calcParams = paramBuilder.withFirst(20)
								.withSecond(4)
								.withOps(Ops.Minus)
								.build();
		
	    String result = callAdd(URL);
		assertThat(result, is("5"));
	}

	private String callAdd(String url) throws JsonProcessingException {
		HttpEntity<String> request = 
				new HttpEntity<String>(calcParams.toJson(), headers);
		
		String result = restTemplate.postForObject(url, 
				request, String.class);
		return result;
	}
}
