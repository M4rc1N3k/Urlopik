package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Printer {
		
	public static List<String[]> offPeriodsDisplay (Map<LocalDate, Integer> yearMap) {
		
		List<String[]> offPeriodsList = new ArrayList<>();
		
		String a="", b="";
		int iterator=0;
		
		for (Map.Entry<LocalDate, Integer> entry : yearMap.entrySet()) {
			
			LocalDate currentDay = entry.getKey();
			LocalDate previousDay = entry.getKey().minusDays(1);
			LocalDate nextDay = entry.getKey().plusDays(1);
			
			if ((iterator==0) || (yearMap.get(currentDay)==1) && (yearMap.get(previousDay)==0))
				a = currentDay.toString();
			if ((iterator==yearMap.size()-1) || (yearMap.get(currentDay)==1) && (yearMap.get(nextDay)==0))
				b = currentDay.toString();
			
			iterator++;
			
			if ((a!="") && (b!="")) {
				String[]x = {a,b};
				offPeriodsList.add(x);
				a="";
				b="";
			}
			
			}
	
		for (String[] name : offPeriodsList) {
			if (name[0].equals(name[1]))
				System.out.println(name[0]);
			else
				System.out.println(name[0]+" - "+name[1]);
		}
		return offPeriodsList;
	}
	
}
