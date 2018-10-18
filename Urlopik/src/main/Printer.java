package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Printer {
		
	public static List<String[]> offPeriodsDisplay (Map<LocalDate, OffMode> yearMap) {
		
		List<String[]> offPeriodsList = new ArrayList<>();
		
		String offPeriodStart="", offPeriodEnd="";
		int precedingDaysCounter=0, followingDaysCounter=0;
		LocalDate tempDay;
		int iterator=0;
		Boolean offLinkedByFreeDaysTrigger = false;
		
		for (Map.Entry<LocalDate, OffMode> entry : yearMap.entrySet()) {
			
			LocalDate currentDay = entry.getKey();
			LocalDate previousDay = entry.getKey().minusDays(1);
			LocalDate nextDay = entry.getKey().plusDays(1);
			
			if ((yearMap.get(currentDay) == OffMode.OFF) && ((iterator==0) || (yearMap.get(previousDay) != OffMode.OFF)))
				{offPeriodStart = currentDay.toString();
				tempDay = currentDay.minusDays(1);
					
				while (((tempDay.getDayOfYear() >= 1) && (tempDay.getYear() == currentDay.getYear())) && (yearMap.get(tempDay) == OffMode.HOLIDAY)) {
					precedingDaysCounter++;
					tempDay = tempDay.minusDays(1);
				}
					
				}
			
			if ((yearMap.get(currentDay) == OffMode.OFF) && ((iterator == yearMap.size()-1) || (yearMap.get(nextDay) != OffMode.OFF)))
				{offPeriodEnd = currentDay.toString();
				tempDay = currentDay.plusDays(1);
				
				while (((tempDay.getDayOfYear() <= (365+(tempDay.isLeapYear() ? 1 : 0))) && (tempDay.getYear() == currentDay.getYear())) && (yearMap.get(tempDay) == OffMode.HOLIDAY)) {
						followingDaysCounter++;
						tempDay = tempDay.plusDays(1);
					}
				}
			
			iterator++;

						if ((offPeriodStart!="") && (offPeriodEnd!="")) {
				String[]offStartEndTable = {offPeriodStart,offPeriodEnd};
				
				if (precedingDaysCounter!=0)
					{String freeDaysTable_part = (!offLinkedByFreeDaysTrigger ? "\n(" : "(" )+precedingDaysCounter+" dni wolne)";
					String[] freeDaysTable = {freeDaysTable_part, freeDaysTable_part};
					offPeriodsList.add(freeDaysTable);
					offLinkedByFreeDaysTrigger=false;
					}
				else
					{String[] freeDaysTable = {"", ""};
					offPeriodsList.add(freeDaysTable);
					}
					
				offPeriodsList.add(offStartEndTable);
				
				if (followingDaysCounter!=0)
					{
						if ((LocalDate.parse(offPeriodEnd).plusDays(followingDaysCounter+1).getYear() == currentDay.getYear()) && (yearMap.get(LocalDate.parse(offPeriodEnd).plusDays(followingDaysCounter+1)) == OffMode.OFF))		//TODO: watch out this part after implementing the following year (boundaries)
						offLinkedByFreeDaysTrigger = true;
					else
						{String freeDaysTable_part = "("+followingDaysCounter+" dni wolne)";
						String[]freeDaysTable = {freeDaysTable_part, freeDaysTable_part};
						offPeriodsList.add(freeDaysTable);}
					}
				
				offPeriodStart="";
				offPeriodEnd="";
				precedingDaysCounter=0;
				followingDaysCounter=0;
			}
			
			
			
			}
		

		for (String[] date : offPeriodsList) {
			if (date[0].equals(date[1]))
				System.out.println(date[0]);
			else
				System.out.println(date[0]+" - "+date[1]);
		}
		return offPeriodsList;
	}
	
}
