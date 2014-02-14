package cz.pfreiberg.knparser.util;

public enum Operations {
	
	lessThan("<"),
	lessThanOrEqual("<="),
	equal("="),
	greaterThan(">"),
	greaterThanOrEqual(">=");
	
	private String operator;

	private Operations(String operator)
	{
		this.operator = operator;
	}

	public String getOperator() {
		return operator;
	}
	
}
