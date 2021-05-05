package testingil.webinar.cleantests;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(Consts.ROOT)
public class CalculatorController {

	@PostMapping(Consts.CALCULATE)
    String calculate(@RequestBody CalculatorParams calcParams) {
        String result ="";
        
        switch (calcParams.getOp()) {
        case Plus:
        	result = Integer.toString(calcParams.getFirst() + calcParams.getSecond());
        	break;
        case Minus:
        	result = Integer.toString(calcParams.getFirst() - calcParams.getSecond());
        	break;
		default:
			result = "Error";
			break;
        	
        }
		return (result);
    }
}
