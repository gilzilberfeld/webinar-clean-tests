package testingil.webinar.cleantests.ex6.abstraction;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import testingil.webinar.cleantests.CalculatorParams;
import testingil.webinar.cleantests.Consts;
import testingil.webinar.cleantests.Ops;

class WithUtils {

	private String URL = "http://localhost:8888";
	private CalculatorParams calcParams;
	private CalcParamBuilder paramBuilder;

	@BeforeEach
	public void setup() {
		URL += Consts.ROOT + Consts.CALCULATE;
	    paramBuilder = new CalcParamBuilder();
	}
	
	@Test
	void add_two_numbers_and_calculate_result() throws Exception {
		calcParams = paramBuilder.withFirst(3).withSecond(4).build(); 
		
	    String result = callCalculate(URL);
		assertThat(result, is("7"));
	}


	@Test
	void add_two_negative_numbers_and_calculate_result() throws Exception {
		calcParams = paramBuilder.withFirst(-5).withSecond(-4).build();
		
	    String result = callCalculate(URL);
		assertThat(result, is("-9"));
		
	}
	
	@Test
	void subtract_two_numbers() throws Exception {
		calcParams = paramBuilder.withFirst(20)
								.withSecond(4)
								.withOps(Ops.Minus)
								.build();
		
	    String result = callCalculate(URL);
		assertThat(result, is("16"));
	}

	public String callCalculate(String url) throws Exception {
		APICallWrapper apiCall = new APICallWrapper();
		String result = apiCall.postWithBody(url, calcParams);
		return result;
	}
}
