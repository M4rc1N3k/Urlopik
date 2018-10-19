package main.resources;

import java.time.LocalDate;

public class trash {

private static int comparingTo (LocalDate end, LocalDate start) {
		
		if (end.getYear()==start.getYear())
			return (end.getDayOfYear() - start.getDayOfYear());																				//difference days if year is same
		else if (end.getYear()>start.getYear())
			return (end.getDayOfYear() + (LocalDate.parse(String.valueOf(start.getYear())+"-12-31").getDayOfYear() - start.getDayOfYear()));		//difference days if year is different
		else
			return -1*(start.getDayOfYear() + (LocalDate.parse(String.valueOf(end.getYear())+"-12-31").getDayOfYear() - end.getDayOfYear()));		//difference days if year is different (start later than end)
	}
	
}
