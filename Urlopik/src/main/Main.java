package main;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		
		
		int curYear = LocalDate.now().getYear();
		int nextYear = LocalDate.now().getYear()+1;
		
		String offBinary = Reader.dataFromFile("off_forCancel.txt");
		int totalOffCount = 90;
		
//		int mode = -1;
		Enums.OperationMode mode;
		Boolean trigger;
		
		Map<LocalDate, Integer> curYearMap = Mapper.generateMap(offBinary, curYear);
		
		Counter curCount = new Counter(curYearMap, totalOffCount);
		int plannedOffCount = curCount.getPlanned();
		int usedOffCount = curCount.getUsed();
		int remainingOffCount = curCount.getRemaining();

		Scanner sc = new Scanner(System.in);

		System.out.println("Witaj w programie ===Urlopik===");
		System.out.println("©2018 by MZ\n");
		
		do {
			trigger = false;
			try {

				curCount = new Counter(curYearMap, totalOffCount);
				plannedOffCount = curCount.getPlanned();
				usedOffCount = curCount.getUsed();
				remainingOffCount = curCount.getRemaining();

				System.out.println("\nTwoje obecnie zaplanowane okresy urlopu to:");
				Printer.offPeriodsDisplay(curYearMap);
				
				System.out.println();
				curCount.summary();
				
				
				System.out.println("\nCo chciałbyś zrobić?");
				System.out.println("\t1) dopisać urlop");
				System.out.println("\t2) anulować urlop");
				System.out.println("\t3) zakończyć program");

//				mode = Integer.parseInt(sc.next());
                System.out.println(Enums.OperationMode.SET);
//				mode = Enums.OperationMode(Integer.parseInt(sc.next()));
				
				if (mode == 1 || mode == 2)
					{curYearMap = Modifier.changeOff(mode, remainingOffCount, plannedOffCount, curYearMap);
					//TODO: zapis mapy do pliku
					}
				else
					{System.out.println("Do widzenia!");
					break;}
				
				System.out.println();

				
			} catch (Exception e) {
//				e.printStackTrace();
				trigger = true;
//				sc.next();
			} 
			
		

		} while (mode != 3 || trigger == true);
		
		sc.close();
		
	}

}
