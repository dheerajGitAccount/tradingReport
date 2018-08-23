import org.junit.Assert;
import org.junit.Test;

/*
 * This file contains main method and will test report Engine and generate Report logic
 */
public class DailyReportingEngineTest {

	/*
	 * Main mehtod will test the entire flow of Reporting Engine
	 * @Exception - Any exception will caught and printed and mark the test case failed.
	 */
	@Test
	public void testDailyReportEngineSuccess() {
		//Data is seeded directly in main program so setting null for input args	
		String [] args = null; 
		try {
		DailyReportingEngine.main(args);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			Assert.fail(ex.getMessage());
		}
	}
}
