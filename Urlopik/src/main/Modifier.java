package main;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Scanner;

public class Modifier {

	public static Map<LocalDate, Integer> changeOff (int mode, int remaining, int planned,  Map<LocalDate, Integer> yearMap) {
		
		Scanner sc = new Scanner(System.in);
		
		LocalDate start=null, end=null;
		

		if ((mode==1) && (remaining==0)){
			System.out.println("Nie masz urlopu do wykorzystania!");
		}
		else if ((mode==2) && (planned==0)){
			System.out.println("Nie masz rozpisanego urlopu do anulowania!");
		}
		else
			
		{	if ((mode==1) && (remaining>0)){
			
			System.out.println("TRYB: Rozpisanie urlopu");
			
		}
		
		
		
		else if ((mode==2) && (planned>0)){
			
			System.out.println("TRYB: Anulowanie urlopu");
			

		}
		
		Boolean validD=false, validA = false, trigger;
		
		
		/* asking for dates */
		do {
			trigger = false;																//exception error trigger
			try {																			//checking the validity of input date format
				System.out.print("Podaj początek w formacie RRRR-MM-DD: ");
				start = LocalDate.parse(sc.nextLine());
				System.out.print("Podaj koniec w formacie RRRR-MM-DD: ");
				end = LocalDate.parse(sc.nextLine());
			
			
				validD = dateValidator(start, end);											//checking the validity of dates (not from the past, in proper order and so on)
				
				if (mode==1) {
					validA = allowanceValidator(start.until(end, ChronoUnit.DAYS), remaining);			//checking if remaining allowance is larger than planned off days
				}
				else validA=true;
			
			} catch (DateTimeParseException e) {
				System.out.println("Błędny format daty!");
				trigger = true;
			}
		
		} while (validD==false || validA==false || trigger==true);
		
		
		for (int i=0;i<=(start.until(end, ChronoUnit.DAYS));i++) {										//iterating from the beginning to the end of off/anulation period

			
			LocalDate curDay = start.plusDays(i);
			
			int curDayStatus = yearMap.get(LocalDate.ofYearDay(curDay.getYear(), curDay.getDayOfYear()));
			
			if(curDayStatus == 0 && mode == 1) {															//checking if current day from the period is working 
				yearMap.put(curDay, 1);																		//setting it to off day
			}
			else if(curDayStatus == 1 && mode == 2) {														//checking if current day from the period is off 
				yearMap.put(curDay, 0);																		//setting it to working day
			}
		}
		
		}
		
//		sc.close();
		System.out.println("Zmiana wprowadzona!");
		return yearMap;
	}
	
	
	
	private static Boolean dateValidator (LocalDate st, LocalDate en) {
		
		if (st.until(en, ChronoUnit.DAYS)<0)
			{System.out.println("Data końca zakresu nie może poprzedzać daty początku zakresu.\n");
			return false;}
//		else if (st.compareTo(LocalDate.now())<0)
//			{System.out.println("Nie można modyfikować urlopu post factum.\n");
//			return false;}
//		else if (en.getYear()>(LocalDate.now().getYear()+1))											//unlock this condition after implementing use of the following year
//			{System.out.println("Można modyfikować urlop nie dalej niż w roku następnym.\n");
		else if (en.getYear()>LocalDate.now().getYear())
			{System.out.println("Można modyfikować urlop nie dalej niż w roku bieżącym.\n");
			return false;}
		else 
			return true;
		
	}
	
	private static Boolean allowanceValidator (long tempPlanned, int remaining) {
		if (tempPlanned > remaining) {
			System.out.println("Nie masz tylu dni do wykorzystania. \nPlanowałeś rozpisać ich "+tempPlanned+" a pozostało zaledwie "+remaining+".\n");
			return false;
		}
		else return true;
	}
	
}
