package testingil.webinar.cleantests;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calc")
public class CalculatorController {

	@PostMapping("/add")
    String add(@RequestBody CalculatorParams calcParams) {
        int result = calcParams.getFirst() + calcParams.getSecond();
		return Integer.toString(result);
    }
}
