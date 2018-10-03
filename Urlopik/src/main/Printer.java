package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Printer {
		
	public static List<String[]> offPeriodsDisplay (Map<LocalDate, Integer> yearMap) {
		
		List<String[]> offPeriodsList = new ArrayList<>();
		
		String a="", b="";
		int c=0, d=0;
		LocalDate e=LocalDate.parse("0000-01-01");
		int f=0;
		LocalDate tempDay;
		int iterator=0;
		
		for (Map.Entry<LocalDate, Integer> entry : yearMap.entrySet()) {
			
			LocalDate currentDay = entry.getKey();
			LocalDate previousDay = entry.getKey().minusDays(1);
			LocalDate nextDay = entry.getKey().plusDays(1);
			
			if ((yearMap.get(currentDay)==1) && ((iterator==0) || (yearMap.get(previousDay)!=1)))					//finds the beginning of a single off period
				{a = currentDay.toString();
				tempDay = currentDay.minusDays(1);
					
				//TODO: boundary conditions to avoid nullpointer exception
				while ((currentDay.getDayOfYear() > 1) && (yearMap.get(tempDay) == 2)) {										//checks how many free days precedes off period
					c++;
					tempDay = tempDay.minusDays(1);
				}
					
				}
			
			if ((yearMap.get(currentDay)==1) && ((iterator==yearMap.size()-1) || (yearMap.get(nextDay)!=1)))		//finds the end of a single off period
				{b = currentDay.toString();
				tempDay = currentDay.plusDays(1);
				
				//TODO: boundary conditions to avoid nullpointer exception	
				while ((currentDay.getDayOfYear() < (365+(tempDay.isLeapYear() ? 1 : 0))) && (yearMap.get(tempDay) == 2)) {		//checks how many free days follows off period
						d++;
						tempDay = tempDay.plusDays(1);
					}
				}
			
			iterator++;
			
			if ((a!="") && (b!="")) {
				String[]x = {a,b};
				
				if (c!=0)
				{
					//TODO: jeśli offPeriodsList(-2)name[1] + offPeriodsList(-1)d + 1 == offPeriodsList(0)name[0]
						//delete new line character (\b)
					//else:
//					System.out.println(e.plusDays(f+1));
//					System.out.println(LocalDate.parse(a)+"\n");
					
					if (e.plusDays(f+1).getDayOfYear() == LocalDate.parse(a).getDayOfYear())
						System.out.println("a \b\r");
					else
						{String[]y = {"\n("+c+" dni wolne)","\n("+c+" dni wolne)"};
						offPeriodsList.add(y);}
				}
					
				offPeriodsList.add(x);
				
				if (d!=0)
					{String[]y = {"("+d+" dni wolne)\n","("+d+" dni wolne)\n"};
					offPeriodsList.add(y);}
				
				f=d;
				e = LocalDate.parse(b);
				
				a="";
				b="";
				c=0;
				d=0;
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
