package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Printer {
		
	public static List<String[]> offPeriodsDisplay (Map<LocalDate, Integer> yearMap) {
		
		List<String[]> offPeriodsList = new ArrayList<>();
		
		String a="", b="";		//for start/end dates of particular off period
		int c=0, d=0;			//for days preceding/following particular off period
		LocalDate tempDay;		//for calculating c and d
		int iterator=0;
		Boolean trig = false;	//for signalizing if off periods are linked by free days
		
		for (Map.Entry<LocalDate, Integer> entry : yearMap.entrySet()) {
			
			LocalDate currentDay = entry.getKey();
			LocalDate previousDay = entry.getKey().minusDays(1);
			LocalDate nextDay = entry.getKey().plusDays(1);
			
			if ((yearMap.get(currentDay)==1) && ((iterator==0) || (yearMap.get(previousDay)!=1)))					//finds the beginning of a single off period
				{a = currentDay.toString();
				tempDay = currentDay.minusDays(1);
					
				while ((currentDay.getDayOfYear() > 1) && (yearMap.get(tempDay) == 2)) {										//checks how many free days precedes off period
					c++;
					tempDay = tempDay.minusDays(1);
				}
					
				}
			
			if ((yearMap.get(currentDay)==1) && ((iterator==yearMap.size()-1) || (yearMap.get(nextDay)!=1)))		//finds the end of a single off period
				{b = currentDay.toString();
				tempDay = currentDay.plusDays(1);
				
				while ((currentDay.getDayOfYear() < (365+(tempDay.isLeapYear() ? 1 : 0))) && (yearMap.get(tempDay) == 2)) {		//checks how many free days follows off period
						d++;
						tempDay = tempDay.plusDays(1);
					}
				}
			
			iterator++;
			
			if ((a!="") && (b!="")) {
				String[]x = {a,b};													//table of two strings marking start and end of an off period
				
				if (c!=0)															//when off period is preceded by free days
					{String y_part = (!trig ? "\n(" : "(" )+c+" dni wolne)";				//when trigger has been switched on in previous iteration, doesn't put new line sign
					String[] y = {y_part, y_part};
					offPeriodsList.add(y);													//adds the information about free days to the list
					trig=false;																//resets the trigger
					}
				else																//when off period is not preceded by free days
					{String[] y = {"", ""};												//just adds blank line to separate it from the previous off
					offPeriodsList.add(y);												
					}
					
				offPeriodsList.add(x);												//adds the off period start/end to the list
				
				if (d!=0)															//when off period is followed by free days
					{
					if (yearMap.get(LocalDate.parse(b).plusDays(d+1))==1)					//when after off period followed by free days another off period starts
						trig = true;															//switches trigger on and omit saving the free days number (they will be added before next off period starts)
					else
						{String y_part = "("+d+" dni wolne)";								
						String[]y = {y_part, y_part};
						offPeriodsList.add(y);}												//adds the information about free days to the list
					}
				
				//resets the values
				a="";
				b="";
				c=0;
				d=0;
			}
			
			
			
			}
		
		//prints the list (if off period is one day only, print its date once)
		for (String[] name : offPeriodsList) {
			if (name[0].equals(name[1]))
				System.out.println(name[0]);
			else
				System.out.println(name[0]+" - "+name[1]);
		}
		return offPeriodsList;
	}
	
}
