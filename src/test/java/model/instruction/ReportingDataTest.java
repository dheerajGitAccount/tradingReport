package model.instruction;


import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
/*
 * Following Test file will test ReportingData POJO fields
 */
public class ReportingDataTest {
	private ReportingData reportingData;
	
	@Before
	public void setup() {
		reportingData = new ReportingData();

	}
	
	@Test
	public void testIncomingReportingData() {
		reportingData.setIsSell(true);
		assertTrue(reportingData.getIsSell());
	}
	
	@Test
	public void testOutgoingReportingData() {
		reportingData.setIsBuy(true);
		assertTrue(reportingData.getIsBuy());
		
	}
}
