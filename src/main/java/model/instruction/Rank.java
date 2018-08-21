package model.instruction;

import java.math.BigDecimal;

/*
 * Rank model
 */
public class Rank implements Comparable<Rank>{

	//Entity - for ranking
	private String entity;
	
	
	//Total amount
	private BigDecimal totalAmount;

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public int compareTo(Rank o) {
		return this.totalAmount.compareTo(o.getTotalAmount());
	}
	
	
	
}
