package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import constants.IConstants;

/*
 * Utility file to perform date adjustments
 */
public class DateCalculation {

	/**
	 * 
	 * @param currency
	 * @return
	 */
	public static Map<DayOfWeek, Boolean> workingDays(Currency currency) {
		if (IConstants.CURRENCY_AED.equalsIgnoreCase(currency.getCurrencyCode())
				|| IConstants.CURRENCY_SAR.equalsIgnoreCase(currency.getCurrencyCode())) {
			// return new RegionalWorkingDays();
			Map<DayOfWeek, Boolean> workingDaysMap = new HashMap<DayOfWeek, Boolean>();
			workingDaysMap.put(DayOfWeek.MONDAY, true);
			workingDaysMap.put(DayOfWeek.TUESDAY, true);
			workingDaysMap.put(DayOfWeek.WEDNESDAY, true);
			workingDaysMap.put(DayOfWeek.THURSDAY, true);
			workingDaysMap.put(DayOfWeek.FRIDAY, false);
			workingDaysMap.put(DayOfWeek.SATURDAY, false);
			workingDaysMap.put(DayOfWeek.SUNDAY, true);
			return workingDaysMap;
		} else {
			Map<DayOfWeek, Boolean> workingDaysMap = new HashMap<DayOfWeek, Boolean>();
			workingDaysMap.put(DayOfWeek.MONDAY, true);
			workingDaysMap.put(DayOfWeek.TUESDAY, true);
			workingDaysMap.put(DayOfWeek.WEDNESDAY, true);
			workingDaysMap.put(DayOfWeek.THURSDAY, true);
			workingDaysMap.put(DayOfWeek.FRIDAY, true);
			workingDaysMap.put(DayOfWeek.SATURDAY, false);
			workingDaysMap.put(DayOfWeek.SUNDAY, false);
			return workingDaysMap;
		}
	}

	/**
	 * Validate the date as date should be on working day else change to next
	 * working day
	 * 
	 * @param workingDate
	 * @param workingWeekDays
	 * @return
	 */
	public static LocalDate setWorkingDay(LocalDate workingDate, Map<DayOfWeek, Boolean> workingWeekDays) {
		DayOfWeek inputDayOfWeek = workingDate.getDayOfWeek();
		if (workingWeekDays.get(inputDayOfWeek)) {
			return workingDate;
		} else {
			workingDate = workingDate.plusDays(1);
			return setWorkingDay(workingDate, workingWeekDays);
		}

	}
}
