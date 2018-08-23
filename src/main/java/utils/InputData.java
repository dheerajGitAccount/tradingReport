package utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import model.instruction.InstructionModel;
import model.instruction.InstructionModel.Action;
/*
 * Sample input file to test the functionality
 */
public class InputData {

	/**
	 * @return
	 */
	public List<InstructionModel> createInputData() {

		/*InstructionModel instructionModelFoo = new InstructionModel();
		instructionModelFoo.setEntity("foo");
		instructionModelFoo.setCurrency(Currency.getInstance("USD"));
		instructionModelFoo.setInstructionDate(LocalDate.of(2016, 01, 10));
		instructionModelFoo.setSettlementDate(LocalDate.of(2006, 01, 12));
		instructionModelFoo.setAgreedForex(BigDecimal.valueOf(.50));
		instructionModelFoo.setTradeAction(Action.BUY);
		instructionModelFoo.setPricePerUnit(BigDecimal.valueOf(100.50));
		instructionModelFoo.setUnits(BigDecimal.valueOf(100));*/

		InstructionModel instructionModelBar = new InstructionModel();
		instructionModelBar.setEntity("bar");
		instructionModelBar.setCurrency(Currency.getInstance("AED"));
		instructionModelBar.setInstructionDate(LocalDate.of(2018, 8, 24));// LocalDate.of(2016, 01, 10));
		instructionModelBar.setSettlementDate(LocalDate.of(2018, 8, 25));
		instructionModelBar.setAgreedForex(BigDecimal.valueOf(.50));
		instructionModelBar.setTradeAction(Action.SELL);
		instructionModelBar.setPricePerUnit(BigDecimal.valueOf(100.50));
		instructionModelBar.setUnits(BigDecimal.valueOf(100));

		List<InstructionModel> instructionList = new ArrayList<InstructionModel>();

		//instructionList.add(instructionModelFoo);
		instructionList.add(instructionModelBar);
		return instructionList;
	}
}
