package main;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class Modifier2 {

	public static Map<LocalDate, Integer> changeOff (int mode, int remaining, int planned,  Map<LocalDate, Integer> yearMap) {
		
		Scanner sc = new Scanner(System.in);
		
		LocalDate start, end;
		
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
		
		Boolean valid = false;
		
		do {
		System.out.print("Podaj początek w formacie RRRR-MM-DD: ");
		start = LocalDate.parse(sc.nextLine());
		System.out.print("Podaj koniec w formacie RRRR-MM-DD: ");
		end = LocalDate.parse(sc.nextLine());
		
		valid = dateValidator(start, end);
		
		} while (valid==false);
		
		System.out.println(end.compareTo(start));
		
		for (int i=0;i<=(end.compareTo(start));i++) {										//iterating from the beginning to the end of off/anulation period
//			int curDayStatus = (int)yearMap.values().toArray()[start.getDayOfYear()+i];		//casting "value" object to integer
			
			LocalDate curDay = start.plusDays(i);
			
			int curDayStatus = yearMap.get(LocalDate.ofYearDay(curDay.getYear(), curDay.getDayOfYear()));
			
						
			if(curDayStatus == 0) {															//checking if current day from the period is working 
				yearMap.put(curDay, 1);														//setting it to off day
				System.out.println("ohui");
			}
		}
		
		}
		
		System.out.println(yearMap.values().toArray()[1]);
		
		sc.close();
		
		return yearMap;
	}
	
	private static Boolean dateValidator (LocalDate st, LocalDate en) {
		
		if (en.compareTo(st)<0)
			return false;
//		else if (st.compareTo(LocalDate.now())<0)
//			return false;
		else if (en.getYear()>LocalDate.now().getYear())
			return false;
		else 
			return true;
		
	}
}
