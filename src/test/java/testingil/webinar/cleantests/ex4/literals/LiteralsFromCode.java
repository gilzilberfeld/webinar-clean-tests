package testingil.webinar.cleantests.ex4.literals;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import testingil.webinar.cleantests.CalculatorParams;
import testingil.webinar.cleantests.Consts;
import testingil.webinar.cleantests.Ops;

class LiteralsFromCode {

	private final String TEST_LOCAL_URL = "http://localhost:8888";
	private String url;
	private CalculatorParams calcParams;
	private HttpHeaders headers;
	private RestTemplate restTemplate;
	private ObjectMapper mapper;

	@BeforeEach
	public void setup() {
		url = TEST_LOCAL_URL + Consts.ROOT + Consts.CALCULATE;
		calcParams = new CalculatorParams();
		headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    restTemplate = new RestTemplate();
	    mapper = new ObjectMapper();
	}
	
	@Test
	void add_two_numbers() throws Exception {
		calcParams.setFirst(3);
		calcParams.setSecond(4);
	    calcParams.setOp(Ops.Plus);
		
	    String result = callCalculate();
		assertThat(result, is("7"));
	}
	
	@Test
	void add_two_negative_numbers() throws Exception {
		calcParams.setFirst(-5);
		calcParams.setSecond(-4);
	    calcParams.setOp(Ops.Plus);
		
	    String result = callCalculate();
		assertThat(result, is("-9"));
		
	}

	@Test
	void subtract_two_numbers() throws Exception {
		calcParams.setFirst(20);
		calcParams.setSecond(4);
		calcParams.setOp(Ops.Minus);
		
	    String result = callCalculate();
		assertThat(result, is("16"));
	}
	
	private String callCalculate() throws Exception {
		String json = mapper.writeValueAsString(calcParams);
		HttpEntity<String> request = new HttpEntity<String>(json, headers);
		
		String result = restTemplate.postForObject(url ,
				request, String.class);
		return result;
	}
}
