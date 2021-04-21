package testingil.webinar.cleantests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CalculatorParams {

	private int first;
	private int second;

	public void addFirst(int firstParam) {
		this.first = firstParam;
	}

	public void addSecond(int secondParam) {
		this.second = secondParam;
	}

	public int getFirst() {
		return first;
	}
	
	public int getSecond() {
		return second;
	}
	
	public String toJson() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}


}
