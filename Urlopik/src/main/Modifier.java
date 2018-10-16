package main;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Scanner;


public class Modifier {

	public static Map<LocalDate, OffMode> changeOff (OperationMode mode, int remaining, int planned, Map<LocalDate, OffMode> yearMap) {

		Scanner sc = new Scanner(System.in);

		LocalDate start=null, end=null;

		if ((mode == OperationMode.SET) && (remaining == 0)){
			System.out.println("Nie masz urlopu do wykorzystania!");
		}
		else if ((mode == OperationMode.CANCEL) && (planned == 0)){
			System.out.println("Nie masz rozpisanego urlopu do anulowania!");
		}
		else

		{	if ((mode == OperationMode.SET) && (remaining > 0)){

			System.out.println("TRYB: Rozpisanie urlopu");

		}



		else if ((mode == OperationMode.CANCEL) && (planned > 0)){

			System.out.println("TRYB: Anulowanie urlopu");


		}

		Boolean validD=false, validA = false, trigger, change=false;


		/* asking for dates */
		do {
			trigger = false;																//exception error trigger
			try {																			//checking the validity of input date format
				System.out.print("Podaj początek w formacie RRRR-MM-DD: ");
				start = LocalDate.parse(sc.nextLine());
				System.out.print("Podaj koniec w formacie RRRR-MM-DD: ");
				end = LocalDate.parse(sc.nextLine());


				validD = dateValidator(start, end);											//checking the validity of dates (not from the past, in proper order and so on)

				if (mode == OperationMode.SET) {
					int plannedWorking = calcEstimatedOffLength(yearMap, start, end);
					validA = allowanceValidator(plannedWorking, remaining);			//checking if remaining allowance is larger than planned off days
				}
				else validA=true;

			} catch (DateTimeParseException e) {
				System.out.println("Błędny format daty!");
				trigger = true;
			}

		} while (validD==false || validA==false || trigger==true);


		for (int i=0;i<=(start.until(end, ChronoUnit.DAYS));i++) {										//iterating from the beginning to the end of off/anulation period


			LocalDate curDay = start.plusDays(i);
			OffMode curDayStatus = yearMap.get(LocalDate.ofYearDay(curDay.getYear(), curDay.getDayOfYear()));

			if(curDayStatus == OffMode.WORKING && mode == OperationMode.SET) {															//checking if current day from the period is working
				yearMap.put(curDay, OffMode.OFF);																		//setting it to off day
				change = true;
			}
			else if(curDayStatus == OffMode.OFF && mode == OperationMode.CANCEL) {														//checking if current day from the period is off
				yearMap.put(curDay, OffMode.WORKING);																		//setting it to working day
				change = true;
			}
		}
		
		if (change == true)
			System.out.println("Zmiana wprowadzona.");
		else
			System.out.println("Brak wprowadzonych zmian.");
		}
		
//		sc.close();
		return yearMap;
	}
	
	
	
	private static Boolean dateValidator (LocalDate st, LocalDate en) {
		
		if (st.until(en, ChronoUnit.DAYS)<0)
			{System.out.println("Data końca zakresu nie może poprzedzać daty początku zakresu.\n");
			return false;}
		else if (st.compareTo(LocalDate.now())<0)														//unlock this condition after finish of testing
			{System.out.println("Nie można modyfikować urlopu post factum.\n");
			return false;}
//		else if (en.getYear()>(LocalDate.now().getYear()+1))											//unlock this condition after implementing use of the following year
//			{System.out.println("Można modyfikować urlop nie dalej niż w roku następnym.\n");
		else if (en.getYear()>LocalDate.now().getYear())
			{System.out.println("Można modyfikować urlop nie dalej niż w roku bieżącym.\n");
			return false;}
		else 
			return true;
		
	}
	
	private static Boolean allowanceValidator (int tempPlanned, int remaining) {
		if (tempPlanned > remaining) {
			System.out.println("Nie masz tylu dni do wykorzystania. \nPlanowałeś rozpisać ich "+tempPlanned+" a pozostało zaledwie "+remaining+".\n");
			return false;
		}
		else return true;
	}

	private static int calcEstimatedOffLength(Map<LocalDate, OffMode> yearMap, LocalDate start, LocalDate end) {

		int estimated=0;
		LocalDate day = start;

		int iterator = start.getDayOfYear();
		while (iterator <= end.getDayOfYear()) {

			if (yearMap.get(day) == OffMode.WORKING) {
				estimated++;
			}

			day = day.plusDays(1);
			iterator++;
		}

		return estimated;
	}
	
}
