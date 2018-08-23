package business;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.instruction.InstructionModel;
import model.instruction.Rank;
import model.instruction.ReportingData;

import utils.DateCalculation;

/*
 * Report Engine will process the data and prepare reporting data
 */
public class ReportEngine { 

	/**
	 * Performs logic to do date adjustment if required
	 * 
	 * @param instructionList
	 * @return
	 */
	public List<InstructionModel> dateCalculation(List<InstructionModel> instructionList) throws Exception{

		instructionList.forEach(instruction -> {
			Map<DayOfWeek, Boolean> workingDaysMap = DateCalculation.workingDays(instruction.getCurrency());
			instruction.setInstructionDate(
					DateCalculation.setWorkingDay(instruction.getInstructionDate(), workingDaysMap));
			instruction
					.setSettlementDate(DateCalculation.setWorkingDay(instruction.getSettlementDate(), workingDaysMap));
		}); 

		return instructionList;
	}

	/**
	 * Performs logic to prepare report for Outgoing/Incoming
	 * 
	 * @param instructionList
	 * @param isBuy
	 * @param isSell
	 * @return
	 */
	public List<ReportingData> prepareReport(List<InstructionModel> instructionList, Boolean isBuy, Boolean isSell)throws Exception {
		List<InstructionModel> tradingList = null;
		if (isBuy) {
			tradingList = instructionList.stream()
					.filter(filter -> filter.getTradeAction().equals(InstructionModel.Action.BUY))
					.collect(Collectors.toList());
		}
		if (isSell) {
			tradingList = instructionList.stream()
					.filter(filter -> filter.getTradeAction().equals(InstructionModel.Action.SELL))
					.collect(Collectors.toList());
		}
		// Group by Date
		Map<LocalDate, List<InstructionModel>> workingDateMap = tradingList.stream()
				.collect(Collectors.groupingBy(d -> d.getSettlementDate()));

	
		List<ReportingData> reportingDataList = new ArrayList<ReportingData>();
		for (Map.Entry<LocalDate, List<InstructionModel>> entry : workingDateMap.entrySet()) {

			BigDecimal totalTradingAmount = entry.getValue().stream().map(InstructionModel::total)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			ReportingData reportingData = new ReportingData();
			reportingData.setDate(entry.getKey());
			reportingData.setTotalAmount(totalTradingAmount);
			reportingData.setIsBuy(isBuy);
			reportingData.setIsSell(isSell);
			reportingDataList.add(reportingData);
		}

		return reportingDataList;
	}

	/**
	 * Performs logic to prepare report for Outgoing/Incoming - Entity Ranking
	 * 
	 * @param instructionList
	 * @param isBuy
	 * @param isSell
	 * @return
	 */
	public Map<LocalDate, List<Rank>> prepareRankingReport(List<InstructionModel> instructionList, boolean isBuy,
			boolean isSell) throws Exception{

		List<InstructionModel> tradingList = null;
		if (isBuy) {
			tradingList = instructionList.stream()
					.filter(filter -> filter.getTradeAction().equals(InstructionModel.Action.BUY))
					.collect(Collectors.toList());
		}
		if (isSell) {
			tradingList = instructionList.stream()
					.filter(filter -> filter.getTradeAction().equals(InstructionModel.Action.SELL))
					.collect(Collectors.toList());
		}

		Map<LocalDate, List<InstructionModel>> workingDateMap = tradingList.stream()
				.collect(Collectors.groupingBy(d -> d.getSettlementDate()));
		Map<LocalDate, List<Rank>> entityRankingMap = new HashMap<LocalDate, List<Rank>>();

		for (Map.Entry<LocalDate, List<InstructionModel>> entry : workingDateMap.entrySet()) {

			// Fetch the unique entity list

			Map<String, List<InstructionModel>> uniqueEntityMap = entry.getValue().stream()
					.collect(Collectors.groupingBy(d -> d.getEntity()));

			List<Rank> entityRankingList = new ArrayList<Rank>();
			for (Map.Entry<String, List<InstructionModel>> entityEntry : uniqueEntityMap.entrySet()) {

				BigDecimal totalAmount = entityEntry.getValue().stream().map(InstructionModel::total)
						.reduce(BigDecimal.ZERO, BigDecimal::add);
				Rank rank = new Rank();
				rank.setEntity(entityEntry.getKey());
				rank.setTotalAmount(totalAmount);
				entityRankingList.add(rank);

			}

			entityRankingMap.put(entry.getKey(), entityRankingList);

		}

		return entityRankingMap;

	}
}
