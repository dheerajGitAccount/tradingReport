package business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Map;

import model.instruction.Rank;
import model.instruction.ReportingData;

/*
 * Following class will show the reports for Outgoing/Incoming Amounts and entity ranking
 */
public class GenerateReport {
 
	/**
	 * Following method will generate outgoing USD Report
	 * 
	 * @param outgoingDataList
	 */
	public void totalOutgoingUSDReport(List<ReportingData> outgoingDataList) throws Exception {

		System.out.println("Total Outgoing Amount Report");
		System.out.println("================================");
		System.out.println("Date       ||     Amount(USD)");
		System.out.println("================================");


		outgoingDataList.forEach(outgoingData -> System.out
				.println(outgoingData.getDate().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) + " ||       "
						+ outgoingData.getTotalAmount()));
	}

	/**
	 * Following method will generate incoming USD Report
	 * 
	 * @param incomingDataList
	 */
	public void totalincomingUSDReport(List<ReportingData> incomingDataList) throws Exception{

		System.out.println("Total Incoming Amount Report");
		System.out.println("================================");
		System.out.println("Date       ||     Amount(USD)");
		System.out.println("================================");

		incomingDataList.forEach(incomingData -> System.out
				.println(incomingData.getDate().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) + " ||       "
						+ incomingData.getTotalAmount()));
	}

	/**
	 * Following method will generate entity ranking for Outgoing
	 * 
	 * @param outgoingRankMap
	 */
	public void entityRankingReportForOutgoing(Map<LocalDate, List<Rank>> outgoingRankMap)throws Exception {

		System.out.println("Ranking Report for Entity(Outgoing) ");

		System.out.println("================================");
		System.out.println("Date         ||     Entity");
		System.out.println("================================");
		for (Map.Entry<LocalDate, List<Rank>> rankEntry : outgoingRankMap.entrySet()) {

			Rank topRank = rankEntry.getValue().stream()
					.sorted((a, b) -> b.getTotalAmount().compareTo(a.getTotalAmount())).findFirst().get();
			System.out.println(rankEntry.getKey().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) + "   ||     "
					+ topRank.getEntity());
		}
	}

	/**
	 * Following method will generate entity ranking for incoming
	 * 
	 * @param incomingRankMap
	 */
	public void entityRankingReportForIncoming(Map<LocalDate, List<Rank>> incomingRankMap)throws Exception {

		System.out.println("Ranking Report for Entity(Incoming) ");

		System.out.println("================================");
		System.out.println("Date         ||     Entity");
		System.out.println("================================");
		for (Map.Entry<LocalDate, List<Rank>> rankEntry : incomingRankMap.entrySet()) {

			Rank topRank = rankEntry.getValue().stream()
					.sorted((a, b) -> b.getTotalAmount().compareTo(a.getTotalAmount())).findFirst().get();
			System.out.println(rankEntry.getKey().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) + "   ||     "
					+ topRank.getEntity());
		}

	}

}
