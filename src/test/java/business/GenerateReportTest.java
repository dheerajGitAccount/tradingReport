package business;

import org.junit.Before;
import org.junit.Test;

import model.instruction.InstructionModel;
import model.instruction.ReportingData;
import model.instruction.InstructionModel.Action;
import model.instruction.Rank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/*
 * This file will test logic added as part of generate report.
 */
public class GenerateReportTest {

	private ReportingData testReportingData;
	private Map<LocalDate, List<Rank>> testIncomingEntityRankMap;
	private Map<LocalDate, List<Rank>> testOutgoingEntityRankMap;
	private GenerateReport generateReport;

	@Before
	public void setup() {
		// ReportingData - Sample - Outgoing- BUY
		testReportingData = new ReportingData();
		testReportingData.setDate(LocalDate.of(2018, 8, 23));
		testReportingData.setIsBuy(true);
		testReportingData.setIsSell(false);
		testReportingData.setTotalAmount(BigDecimal.valueOf(5025.50));

		// Ranking Data - Sample - Incoming
		testIncomingEntityRankMap = new HashMap<LocalDate, List<Rank>>();
		List<Rank> incomingRankList = new ArrayList<Rank>();
		Rank incomingRankValueFoo = new Rank();
		incomingRankValueFoo.setEntity("foo");
		incomingRankValueFoo.setTotalAmount(BigDecimal.valueOf(1010.00));
		incomingRankList.add(incomingRankValueFoo);
		Rank incomingRankValueBar = new Rank();
		incomingRankValueBar.setEntity("bar");
		incomingRankValueBar.setTotalAmount(BigDecimal.valueOf(1000.00));
		incomingRankList.add(incomingRankValueBar);
		testIncomingEntityRankMap.put(LocalDate.of(2018, 8, 23), incomingRankList);

		// Ranking Data - Sample - Outgoing
		testOutgoingEntityRankMap = new HashMap<LocalDate, List<Rank>>();
		List<Rank> outgoingRankList = new ArrayList<Rank>();
		Rank outgoingRankValueFoo = new Rank();
		outgoingRankValueFoo.setEntity("foo");
		outgoingRankValueFoo.setTotalAmount(BigDecimal.valueOf(1010.00));
		outgoingRankList.add(outgoingRankValueFoo);
		Rank outgoingRankValueBar = new Rank();
		outgoingRankValueBar.setEntity("bar");
		outgoingRankValueBar.setTotalAmount(BigDecimal.valueOf(1000.00));
		outgoingRankList.add(outgoingRankValueBar);
		testOutgoingEntityRankMap.put(LocalDate.of(2018, 8, 23), outgoingRankList);

		generateReport = new GenerateReport();
	}

	/**
	 * Run the outgoing USD Report
	 * 
	 */
	@Test
	public void testTotalOutgoingUSDReport() {
		try {
			List<ReportingData> outgoingDataList = new ArrayList<ReportingData>();
			outgoingDataList.add(testReportingData);

			generateReport.totalOutgoingUSDReport(outgoingDataList);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Run the incoming USD Report
	 * 
	 */
	@Test
	public void testTotalIncomingUSDReport() {
		try {
			List<ReportingData> incomingDataList = new ArrayList<ReportingData>();
			testReportingData.setIsBuy(false);
			testReportingData.setIsSell(true);
			incomingDataList.add(testReportingData);

			generateReport.totalincomingUSDReport(incomingDataList);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	
	/**
	 * Run the entity ranking outgoing Report
	 * 
	 */
	@Test
	public void testEntityRankingReportForOutgoing() {
		try {
			generateReport.entityRankingReportForOutgoing(testOutgoingEntityRankMap);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	/**
	 * Run the entity ranking incoming Report
	 * 
	 */
	@Test
	public void testEntityRankingReportForIncoming() {
		try {
			generateReport.entityRankingReportForIncoming(testIncomingEntityRankMap);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

}
