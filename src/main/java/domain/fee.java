package domain;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class fee {
	private Float fee;
	@Id
	private String result;

	public fee(Float fee, String result) {
		this.fee = fee;
		this.result = result;
	}
	
	public String getResult() {
		return this.result;
	}
}
