package testingil.webinar.cleantests.ex7.differentiation;

import testingil.webinar.cleantests.CalculatorParams;
import testingil.webinar.cleantests.Ops;

public class CalcParamBuilder {
	
	private int second=0;
	private int first=0;
	private Ops op=Ops.Plus;

	public CalcParamBuilder withFirst(int first) {
		this.first= first;
		return this;
	}
	
	public CalcParamBuilder withSecond(int second) {
		this.second= second;
		return this;
	}
	
	public CalcParamBuilder withOps(Ops op) {
		this.op = op;
		return this;
	}
	
	public CalculatorParams build() {
		CalculatorParams params = new CalculatorParams();
		params.setFirst(first);
		params.setSecond(second);
		params.setOp(op);
		return params;
	}
}
