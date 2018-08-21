package model.instruction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
/*
 * Instruction model 
 */
public class InstructionModel implements Comparable<InstructionModel>{

	//Action
	public enum Action{
		BUY("B"),
		SELL("S");
		
		private String actionValue;
		
		Action(String actionValue){
			this.actionValue=actionValue;
		}
		
		public String getAction() {
			return this.actionValue;
		}
	}
	
	//Entity
	private String entity;
	
	//settlementDate
	private LocalDate settlementDate;
	
	//instructionDate
	private LocalDate instructionDate;
	
	//Forex
	private BigDecimal agreedForex;
	
	//tradeAction
	private Action tradeAction;
	
	//Price per unit
	private BigDecimal pricePerUnit;
	
	//Units
	private BigDecimal units;
	
	//Currency
	private Currency currency;

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}

	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}

	public BigDecimal getAgreedForex() {
		return agreedForex;
	}

	public void setAgreedForex(BigDecimal agreedForex) {
		this.agreedForex = agreedForex;
	}

	public Action getTradeAction() {
		return tradeAction;
	}

	public void setTradeAction(Action tradeAction) {
		this.tradeAction = tradeAction;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public BigDecimal getUnits() {
		return units;
	}

	public void setUnits(BigDecimal units) {
		this.units = units;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	public BigDecimal total() {
		return this.pricePerUnit.multiply(this.agreedForex).multiply(this.units);
	}

	
	@Override
	public int compareTo(InstructionModel o) {
		return this.getSettlementDate().compareTo(o.getSettlementDate());
	}
}
