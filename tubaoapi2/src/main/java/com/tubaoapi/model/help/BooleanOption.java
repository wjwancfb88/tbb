package com.tubaoapi.model.help;

public enum BooleanOption {
	TRUE("是"), FLASE("否");
	

	public String label;

	BooleanOption(String label) {
		this.label = label;
	}

	public String getName() {
		return name();
	}
	
	public Boolean getValue() {
		return new Boolean(name());
	}

	public String getLabel() {
		return label;
	}
	
}
