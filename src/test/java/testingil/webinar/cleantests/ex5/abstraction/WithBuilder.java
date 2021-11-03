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
import com.fasterxml.jackson.databind.ObjectMapper;

import testingil.webinar.cleantests.CalculatorParams;
import testingil.webinar.cleantests.Consts;
import testingil.webinar.cleantests.Ops;

class WithBuilder {

	private final String LOCAL_URL = "http://localhost:8888";
	private String url;
	private CalculatorParams calcParams;
	private HttpHeaders headers;
	private RestTemplate restTemplate;
	private CalcParamBuilder paramBuilder;
	private ObjectMapper mapper;

	@BeforeEach
	public void setup() {
		url = LOCAL_URL + Consts.ROOT + Consts.CALCULATE;
		headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    restTemplate = new RestTemplate();
		paramBuilder = new CalcParamBuilder();
	    mapper = new ObjectMapper();
	}
	
	@Test
	void add_two_numbers_and_calculate_result() throws Exception {
		calcParams = paramBuilder.withFirst(3)
				.withSecond(4).build(); 
		
	    String result = callCalculate();
		assertThat(result, is("7"));
	}


	@Test
	void add_two_negative_numbers_and_calculate_result() throws Exception {
		calcParams = paramBuilder.withFirst(-5)
				.withSecond(-4).build();
		
	    String result = callCalculate();
		assertThat(result, is("-9"));
		
	}
	
	@Test
	void subtract_two_numbers() throws Exception {
		calcParams = paramBuilder.withFirst(20)
								.withSecond(4)
								.withOps(Ops.Minus)
								.build();
		
	    String result = callCalculate();
		assertThat(result, is("16"));
	}

	private String callCalculate() throws Exception {
		String json = mapper.writeValueAsString(calcParams);
		HttpEntity<String> request = new HttpEntity<String>(json, headers);

		String result = restTemplate.postForObject(url,
				request, String.class);
		return result;
	}
}
