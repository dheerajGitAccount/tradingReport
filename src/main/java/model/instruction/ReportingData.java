package model.instruction;

import java.math.BigDecimal;
import java.time.LocalDate;
/*
 * Reporting Data model file
 */
public class ReportingData {
	private LocalDate date;
	private BigDecimal totalAmount;
	private Boolean isBuy;
	private Boolean isSell;
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public Boolean getIsBuy() {
		return isBuy;
	}
	public void setIsBuy(Boolean isBuy) {
		this.isBuy = isBuy;
	}
	public Boolean getIsSell() {
		return isSell;
	}
	public void setIsSell(Boolean isSell) {
		this.isSell = isSell;
	}
	
	
}
