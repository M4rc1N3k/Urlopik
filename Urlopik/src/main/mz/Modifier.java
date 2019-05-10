import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Scanner;


public class Modifier {

	private Map<LocalDate, OffMode> modifiedYearMap;

	public Modifier(OperationMode mode, int remaining, int planned, Map<LocalDate, OffMode> yearMap) {
		this.modifiedYearMap = changeOff(mode, remaining, planned, yearMap);
	}

	public Map<LocalDate, OffMode> getModifiedYearMap() {
		return this.modifiedYearMap;
	}

	private Map<LocalDate, OffMode> changeOff (OperationMode mode, int remaining, int planned, Map<LocalDate, OffMode> yearMap) {

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

		Boolean validD=false, validA = false, yearMapChange=false;


		do {
			try {
				System.out.print("Podaj początek w formacie RRRR-MM-DD: ");
				start = LocalDate.parse(sc.nextLine());
				System.out.print("Podaj koniec w formacie RRRR-MM-DD: ");
				end = LocalDate.parse(sc.nextLine());


				validD = dateValidator(start, end);

				validA = allowanceValidator(mode, yearMap, start, end, remaining);


			} catch (DateTimeParseException e) {
				System.out.println("Błędny format daty!");
			}

		} while (multipleValidator(validA, validD) == false);

		for (int i=0;i<=(start.until(end, ChronoUnit.DAYS));i++) {


			LocalDate currentDay = start.plusDays(i);
			OffMode currentDayStatus = yearMap.get(LocalDate.ofYearDay(currentDay.getYear(), currentDay.getDayOfYear()));

			if(currentDayStatus == OffMode.WORKING && mode == OperationMode.SET) {
				yearMap.put(currentDay, OffMode.OFF);
				yearMapChange = true;
			}
			else if(currentDayStatus == OffMode.OFF && mode == OperationMode.CANCEL) {
				yearMap.put(currentDay, OffMode.WORKING);
				yearMapChange = true;
			}
		}
		
		if (yearMapChange == true)
			System.out.println("Zmiana wprowadzona.");
		else
			System.out.println("Brak wprowadzonych zmian.");
		}
		
//		sc.close();
		return yearMap;
	}
	
	
	
	private Boolean dateValidator (LocalDate st, LocalDate en) {
		
		if (st.until(en, ChronoUnit.DAYS)<0)
			{System.out.println("Data końca zakresu nie może poprzedzać daty początku zakresu.\n");
			return false;}
		else if (st.compareTo(LocalDate.now())<0)
			{System.out.println("Nie można modyfikować urlopu post factum.\n");
			return false;}
//		else if (en.getYear()>(LocalDate.now().getYear()+1))											//TODO: unlock this condition after implementing use of the following year
//			{System.out.println("Można modyfikować urlop nie dalej niż w roku następnym.\n");
		else if (en.getYear()>LocalDate.now().getYear())
			{System.out.println("Można modyfikować urlop nie dalej niż w roku bieżącym.\n");
			return false;}
		else {
			return true;
		}
		
	}
	
	private Boolean allowanceValidator (OperationMode mode, Map<LocalDate, OffMode> yearMap, LocalDate start, LocalDate end, int remaining) {

		if (mode == OperationMode.CANCEL) {
			return true;
		} else
			{int tempEstimated = calcEstimatedOffLength(yearMap, start, end);

			if (tempEstimated > remaining)
				{System.out.println("Nie masz tylu dni do wykorzystania. \nPlanowałeś rozpisać ich "+tempEstimated+" a pozostało zaledwie "+remaining+".\n");
				return false;}

			else {
				return true;
			}
			}
	}

	private int calcEstimatedOffLength(Map<LocalDate, OffMode> yearMap, LocalDate start, LocalDate end) {

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

	private static Boolean multipleValidator (Boolean validAllowance, Boolean validDate){

		if (validAllowance && validDate) {
			return true;
		} else {
			return false;
		}

	}

}
