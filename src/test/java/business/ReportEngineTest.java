package business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import junit.framework.AssertionFailedError;
import model.instruction.InstructionModel;
import model.instruction.Rank;
import model.instruction.ReportingData;
import model.instruction.InstructionModel.Action;
import utils.DateCalculation;
import utils.InputData;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/*
 * This file will test logic added as part of report engine.
 */
public class ReportEngineTest {

	private InstructionModel testInstruction;
	private InstructionModel testInstructionBuy;
	private InstructionModel testInstructionSell;
	private ReportEngine reportEngine;
 
	@Before
	public void setup() {

		// Test Data of InstructionModel
		testInstruction = new InstructionModel();
		testInstruction.setEntity("foo");
		testInstruction.setCurrency(Currency.getInstance("USD"));
		testInstruction.setInstructionDate(LocalDate.of(2018, 8, 24));
		testInstruction.setSettlementDate(LocalDate.of(2018, 8, 25)); // Settlement Day is Saturday
		testInstruction.setAgreedForex(BigDecimal.valueOf(.50));
		testInstruction.setTradeAction(Action.SELL);
		testInstruction.setPricePerUnit(BigDecimal.valueOf(100.50));
		testInstruction.setUnits(BigDecimal.valueOf(100));

		// Test Data of InstructionModel - BUY
		testInstructionBuy = new InstructionModel();
		testInstructionBuy.setEntity("foo");
		testInstructionBuy.setCurrency(Currency.getInstance("USD"));
		testInstructionBuy.setInstructionDate(LocalDate.of(2018, 8, 21));
		testInstructionBuy.setSettlementDate(LocalDate.of(2018, 8, 23)); // Settlement Day is Saturday
		testInstructionBuy.setAgreedForex(BigDecimal.valueOf(.50));
		testInstructionBuy.setTradeAction(Action.BUY);
		testInstructionBuy.setPricePerUnit(BigDecimal.valueOf(100.50));
		testInstructionBuy.setUnits(BigDecimal.valueOf(100));

		// Test Data of InstructionModel - SELL
		testInstructionSell = new InstructionModel();
		testInstructionSell.setEntity("bar");
		testInstructionSell.setCurrency(Currency.getInstance("USD"));
		testInstructionSell.setInstructionDate(LocalDate.of(2018, 8, 21));
		testInstructionSell.setSettlementDate(LocalDate.of(2018, 8, 23)); // Settlement Day is Saturday
		testInstructionSell.setAgreedForex(BigDecimal.valueOf(.50));
		testInstructionSell.setTradeAction(Action.SELL);
		testInstructionSell.setPricePerUnit(BigDecimal.valueOf(100.50));
		testInstructionSell.setUnits(BigDecimal.valueOf(100));

		reportEngine = new ReportEngine();
	}

	/**
	 * input Day -> Saturday and expected Day -> Monday for Default working days.
	 * Result -> SUCCESS
	 */
	@Test
	public void testDateCalculationDefaultSaturday() {
		try {
			List<InstructionModel> testInstructionList = new ArrayList<InstructionModel>();
			testInstructionList.add(testInstruction);

			List<InstructionModel> returnedInstructionList = reportEngine.dateCalculation(testInstructionList);
			returnedInstructionList.forEach(instructionList -> {
				assertEquals(LocalDate.of(2018, 8, 27), instructionList.getSettlementDate()); // return true -> expected
																								// day
																								// matches with returned
																								// day
			});
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			Assert.fail(ex.getMessage());
		}

	}

	/**
	 * input Day -> Sunday and expected Day -> Monday for Default working days.
	 * Result -> SUCCESS
	 */
	@Test
	public void testDateCalculationDefaultSunday() {
		try {
			List<InstructionModel> testInstructionList = new ArrayList<InstructionModel>();
			testInstruction.setSettlementDate(LocalDate.of(2018, 8, 26)); // Settlement Day is Sunday
			testInstructionList.add(testInstruction);

			List<InstructionModel> returnedInstructionList = reportEngine.dateCalculation(testInstructionList);
			returnedInstructionList.forEach(instructionList -> {
				assertEquals(LocalDate.of(2018, 8, 27), instructionList.getSettlementDate()); // return true -> expected
																								// day
																								// matches with returned
																								// day
			});
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			Assert.fail(ex.getMessage());
		}
	}

	/**
	 * input Day -> Friday and expected Day -> Sunday for Saudi working days. Result
	 * -> SUCCESS
	 */
	@Test
	public void testDateCalculationSaudiFriday() {
		try {
			List<InstructionModel> testInstructionList = new ArrayList<InstructionModel>();
			testInstruction.setCurrency(Currency.getInstance("AED")); // Saudi Currency
			testInstruction.setSettlementDate(LocalDate.of(2018, 8, 24)); // Settlement Day is Friday
			testInstructionList.add(testInstruction);

			List<InstructionModel> returnedInstructionList = reportEngine.dateCalculation(testInstructionList);
			returnedInstructionList.forEach(instructionList -> {
				assertEquals(LocalDate.of(2018, 8, 26), instructionList.getSettlementDate()); // return true -> expected
																								// day
																								// matches with returned
																								// day
			});

			testInstruction.setCurrency(Currency.getInstance("SAR")); // Saudi Currency
			testInstruction.setSettlementDate(LocalDate.of(2018, 8, 24)); // Settlement Day is Friday
			returnedInstructionList = reportEngine.dateCalculation(testInstructionList);
			returnedInstructionList.forEach(instructionList -> {
				assertEquals(LocalDate.of(2018, 8, 26), instructionList.getSettlementDate()); // return true -> expected
																								// day
																								// matches with returned
																								// day
			});
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			Assert.fail(ex.getMessage());
		}

	}

	/**
	 * input Day -> Saturday and expected Day -> Sunday for Saudi working days.
	 * Result -> SUCCESS
	 */
	@Test
	public void testDateCalculationSaudiSaturday() {
		try {
			List<InstructionModel> testInstructionList = new ArrayList<InstructionModel>();
			testInstruction.setCurrency(Currency.getInstance("AED")); // Saudi Currency
			testInstruction.setSettlementDate(LocalDate.of(2018, 8, 25)); // Settlement Day is Saturday
			testInstructionList.add(testInstruction);

			List<InstructionModel> returnedInstructionList = reportEngine.dateCalculation(testInstructionList);
			returnedInstructionList.forEach(instructionList -> {
				assertEquals(LocalDate.of(2018, 8, 26), instructionList.getSettlementDate()); // return true -> expected
																								// day
																								// matches with returned
																								// day
			});
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			Assert.fail(ex.getMessage());
		}

	}

	/*
	 * Test - daily incoming report amount in USD for each date
	 * Result -> SUCCESS
	 */
	@Test
	public void testDailyIncomingReport() {
		try {
			List<InstructionModel> incomingReportList = new ArrayList<InstructionModel>();
			incomingReportList.add(testInstructionBuy);
			List<ReportingData> returnedIncomingReportList = reportEngine.prepareReport(incomingReportList, true,
					false);
			returnedIncomingReportList.forEach(reportingData -> {
				assertEquals(LocalDate.of(2018, 8, 23), reportingData.getDate());
				assertEquals(BigDecimal.valueOf(5025.00).setScale(2, BigDecimal.ROUND_HALF_EVEN),
						reportingData.getTotalAmount());
			});
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			Assert.fail(ex.getMessage());
		}

	}
	/*
	 * Test - daily outgoing report amount in USD for each date
	 * Result -> SUCCESS
	 */
	@Test
	public void testDailyOutgoingReport() {
		try {
			List<InstructionModel> outgoingReportList = new ArrayList<InstructionModel>();
			outgoingReportList.add(testInstructionSell);
			List<ReportingData> returnedIncomingReportList = reportEngine.prepareReport(outgoingReportList, false,
					true);
			returnedIncomingReportList.forEach(reportingData -> {
				assertEquals(LocalDate.of(2018, 8, 23), reportingData.getDate());
				assertEquals(BigDecimal.valueOf(5025.00).setScale(2, BigDecimal.ROUND_HALF_EVEN),
						reportingData.getTotalAmount());
			});
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			Assert.fail(ex.getMessage());
		}

	}

	/*
	 * Test - daily outgoing rank report of entity for each date
	 * Result -> SUCCESS
	 */
	@Test
	public void testDailyOutgoingEntityRank() {
		try {
			List<InstructionModel> outgoingReportList = new ArrayList<InstructionModel>();
			outgoingReportList.add(testInstructionBuy);
			Map<LocalDate, List<Rank>> returnedIncomingRankList = reportEngine.prepareRankingReport(outgoingReportList,
					true, false);

			returnedIncomingRankList.forEach((date, rank) -> {

				assertEquals(LocalDate.of(2018, 8, 23), date);

				rank.forEach(rankValue -> {
					assertEquals("foo", rankValue.getEntity());
				});

			});
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			Assert.fail(ex.getMessage());
		}

	}

	/*
	 * /*
	 * Test - daily incoming rank report of entity for each date
	 * Result -> SUCCESS
	 */
	@Test
	public void testDailyIncomingEntityRank() {
		try {
			List<InstructionModel> incomingReportList = new ArrayList<InstructionModel>();
			incomingReportList.add(testInstructionSell);
			Map<LocalDate, List<Rank>> returnedIncomingRankList = reportEngine.prepareRankingReport(incomingReportList,
					false, true);

			returnedIncomingRankList.forEach((date, rank) -> {

				assertEquals(LocalDate.of(2018, 8, 23), date);

				rank.forEach(rankValue -> {
					assertEquals("bar", rankValue.getEntity());
				});

			});
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			Assert.fail(ex.getMessage());
		}

	}

}
