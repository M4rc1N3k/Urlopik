package main;

import java.time.LocalDate;
import java.util.Map;
import static main.Enums.OffMode;

public class Counter {

	private static LocalDate today = LocalDate.now();
	private static int todayNumber = today.getDayOfYear();
	
	private int total, planned, used, remaining;
	
			
	public Counter(Map<LocalDate, Enum> yearMap, int total) {
		this.total = total;
		this.planned = calcPlanned(yearMap);
		this.used = calcUsed(yearMap);
		this.remaining = calcRemaining(total, yearMap);
	}
	
	private static int calcPlanned (Map<LocalDate, Enum> yearMap) {
		
		int planned=0;
		LocalDate day = today;
		
		int iterator = todayNumber;
		while (iterator<=yearMap.size()) {
			
			if (yearMap.get(day) == OffMode.OFF) {
				planned++;
			}
			
			day = day.plusDays(1);
			iterator++;
		}
	
	return planned;
	}
	
	private static int calcUsed (Map<LocalDate, Enum> yearMap) {
		
		int used=0;
		LocalDate day = LocalDate.parse(String.valueOf(today.getYear()+"-01-01"));
		
		int iterator = 1;
		while (iterator<=todayNumber) {
			if (yearMap.get(day) == OffMode.OFF) {
				used++;
			}
			day = day.plusDays(1);
			iterator++;
		}
	
	return used;
	}
	
	private static int calcRemaining (int total, Map<LocalDate, Enum> yearMap) {
		int remaining = total - calcUsed(yearMap) - calcPlanned(yearMap);
		
		return remaining;
	}

	public void summary () {
		System.out.println("Ogółem urlopu "+getTotal()+" dni, w tym:");
		System.out.println("\t-zaplanowano "+getPlanned()+" dni");
		System.out.println("\t-wykorzystano "+getUsed()+" dni");
		System.out.println("\t-pozostało "+getRemaining()+" dni");
	}

	public int getTotal() {
		return total;
	}



	public int getPlanned() {
		return planned;
	}



	public int getUsed() {
		return used;
	}



	public int getRemaining() {
		return remaining;
	}
}
