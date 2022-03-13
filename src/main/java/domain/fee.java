package domain;

import javax.persistence.Entity;

public class fee {
	private Float fee;
	private String result;

	public fee(Float fee, String result) {
		this.fee = fee;
		this.result = result;
	}
}
