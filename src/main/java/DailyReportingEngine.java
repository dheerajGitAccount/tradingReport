import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import business.GenerateReport;
import business.ReportEngine;
import model.instruction.InstructionModel;
import model.instruction.Rank;
import model.instruction.ReportingData;
import utils.InputData;

/**
 * Daily Reporting Engine
 *
 */
public class DailyReportingEngine { 
 
	public static void main(String[] args) throws Exception{
		
		// Step 1 - create input data - in-memory
		InputData seededData = new InputData();
		List<InstructionModel> instructionList = seededData.createInputData();

		// Step 2 - Call Report Engine to process and prepare the data
		ReportEngine reportEngine = new ReportEngine();
		// working days calculation
		instructionList = reportEngine.dateCalculation(instructionList);

		// Outgoing/Incoming Amount
		List<ReportingData> outgoingDataList = reportEngine.prepareReport(instructionList, true, false);
		List<ReportingData> incomingDataList = reportEngine.prepareReport(instructionList, false, true);

		// Entity ranking report
		Map<LocalDate, List<Rank>> outgoingRankMap = reportEngine.prepareRankingReport(instructionList, true, false);
		Map<LocalDate, List<Rank>> incomingRankMap = reportEngine.prepareRankingReport(instructionList, false, true);

		// Step 3 - Generate Reports
		GenerateReport generateReport = new GenerateReport();

		generateReport.totalOutgoingUSDReport(outgoingDataList);
		System.out.println("================================== \n\n");

		generateReport.totalincomingUSDReport(incomingDataList);
		System.out.println("================================== \n\n");

		generateReport.entityRankingReportForOutgoing(outgoingRankMap);
		System.out.println("================================== \n\n");

		generateReport.entityRankingReportForIncoming(incomingRankMap);
		System.out.println("================================== \n\n");
		
	}

}
