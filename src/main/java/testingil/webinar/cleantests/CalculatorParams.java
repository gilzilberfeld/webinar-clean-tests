package testingil.webinar.cleantests;

public class CalculatorParams {

	private int first;
	private int second;
	private Ops op;

	public void setFirst(int firstParam) {
		this.first = firstParam;
	}

	public void setSecond(int secondParam) {
		this.second = secondParam;
	}

	public int getFirst() {
		return first;
	}
	
	public int getSecond() {
		return second;
	}
	

	public void setOp (Ops op) {
		this.op = op;
	}
	
	public Ops getOp() {
		return this.op;
	}

}
