package model.instruction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;

/*
 * Following Test file will test InstructionModel POJO fields
 */
public class InstructionModelTest {

	private InstructionModel instruction;
	
	@Before
	public void setup() {
		instruction = new InstructionModel();
		instruction.setSettlementDate(LocalDate.of(2018, 8, 23));
	}
	
	@Test
	public void testInstructionModel() {
		//AgreedForex
		instruction.setAgreedForex(BigDecimal.valueOf(100.00).setScale(2, BigDecimal.ROUND_HALF_EVEN));
		assertEquals(BigDecimal.valueOf(100.0).setScale(2, BigDecimal.ROUND_HALF_EVEN), instruction.getAgreedForex());
		
		//
		instruction.setCurrency(Currency.getInstance("USD"));
		assertEquals(Currency.getInstance("USD"), instruction.getCurrency());
		
		//PricePerunit
		instruction.setPricePerUnit(BigDecimal.valueOf(100.00).setScale(2, BigDecimal.ROUND_HALF_EVEN));
		assertEquals(BigDecimal.valueOf(100.0).setScale(2, BigDecimal.ROUND_HALF_EVEN), instruction.getPricePerUnit());
		
		
		//Unit
		instruction.setUnits(BigDecimal.valueOf(100.00).setScale(2, BigDecimal.ROUND_HALF_EVEN));
		assertEquals(BigDecimal.valueOf(100.0).setScale(2, BigDecimal.ROUND_HALF_EVEN), instruction.getUnits());
		
		//compareTo
		InstructionModel compareInstructionObj = new InstructionModel();
		compareInstructionObj.setSettlementDate(LocalDate.of(2018, 8, 23));
		assertNotNull(instruction.compareTo(compareInstructionObj));
		
		Enum tradeAction = InstructionModel.Action.valueOf("BUY");
		assertNotNull(tradeAction);
		
	}
	
}
