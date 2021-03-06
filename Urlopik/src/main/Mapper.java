package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Mapper {
	
	
	
	public static Map<LocalDate, Integer> generateMap (String offBinary, int year) {
		int a = offBinary.length();
		char array[] = offBinary.toCharArray();
		List <Integer> listInt = new ArrayList<Integer>(); 
		
		
		
		for (int i=0;i<a;i++) {
			int candidate = Character.getNumericValue(array[i]);
			if (candidate == 0 || candidate == 1 || candidate == 2) //0-working, 1-off, 2-holiday
				listInt.add(candidate);
		}
		
		int leapYearAppx = isLeapYear(year) ? 1 : 0;
		int yearLength = 365 + leapYearAppx; 
	
	List<LocalDate> holidaysList = Holidays.holidaysList(String.valueOf(year));
	
	LocalDate date = LocalDate.parse(String.valueOf(year+"-01-01"));	
	Map<LocalDate, Integer>yearMap = new TreeMap<>();
		for (int i=0;i<yearLength;i++) {
			if (isHoliday(date, holidaysList))
				yearMap.put(date, 2); //digit "2" marks holiday
			else
				yearMap.put(date, listInt.get(i));
			date=date.plusDays(1);
		}
	
	//TODO: mapping the following year	
		
	return yearMap;

	
	}
	
	private static Boolean isLeapYear(int year) {
		if ((year%4 == 0  &&  year%100!=0) || (year%400 == 0)) //condition for a leap year
			return Boolean.TRUE;
		else
			return Boolean.FALSE;
	}
	
	private static Boolean isHoliday (LocalDate date, List<LocalDate> holidaysList) {
		
		for (LocalDate holiday : holidaysList) {
			if (holiday.compareTo(date) == 0)
				return Boolean.TRUE;
		}
		
		if (date.getDayOfWeek().toString()=="SATURDAY" || date.getDayOfWeek().toString()=="SUNDAY")
			return Boolean.TRUE;
		else
			return Boolean.FALSE;
	}
	
}
