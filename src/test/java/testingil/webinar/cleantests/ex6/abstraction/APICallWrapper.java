package testingil.webinar.cleantests.ex6.abstraction;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APICallWrapper {

	private HttpHeaders headers;
	private RestTemplate restTemplate;
	private ObjectMapper mapper;
	
	public APICallWrapper() {
		headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    restTemplate = new RestTemplate();
	    mapper = new ObjectMapper();
	}

	public String postWithBody(String url, Object body) throws Exception {
		String json = mapper.writeValueAsString(body);
		HttpEntity<String> request = new HttpEntity<String>(json, headers);
		
		String result = restTemplate.postForObject(url, 
				request, String.class);
		return result;
	}
}
