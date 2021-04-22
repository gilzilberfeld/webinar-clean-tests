package testingil.webinar.cleantests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import testingil.webinar.cleantests.CalculatorController;
import testingil.webinar.cleantests.CalculatorParams;

@SpringBootTest
class ControllerTest {

	MockMvc mvc;
	
	@Test
	void test() throws Exception {
		this.mvc = MockMvcBuilders.standaloneSetup(
				new CalculatorController()).build();
		
		CalculatorParams calcparams = new CalculatorParams();
		calcparams.setFirst(1);
		calcparams.setSecond(2);
		calcparams.setOp(Ops.Plus);
		
		String json = calcparams.toJson();
		MvcResult result = mvc.perform(post("/root/calculate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk())
				.andReturn();
		assertThat(result.getResponse().getContentAsString(), is("3"));
	}

}
