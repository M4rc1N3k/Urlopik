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

		OperationMode mode = OperationMode.DEFAULT;
		Boolean errorTrigger;

		Map<LocalDate, OffMode> curYearMap = Mapper.generateMap(offBinary, curYear);

		Counter curCount = new Counter(curYearMap, totalOffCount);
		int plannedOffCount = curCount.getPlanned();
		int usedOffCount = curCount.getUsed();
		int remainingOffCount = curCount.getRemaining();

		Scanner sc = new Scanner(System.in);

		System.out.println("Witaj w programie ===Urlopik===");
		System.out.println("©2018 by MZ\n");

		do {
			errorTrigger = false;
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

				mode = OperationMode.fromInt(Integer.parseInt(sc.next()));

				if (mode == OperationMode.SET || mode == OperationMode.CANCEL)
					{curYearMap = Modifier.changeOff(mode, remainingOffCount, plannedOffCount, curYearMap);
					//TODO: saving map to the output file
					}
				else if (mode == OperationMode.EXIT)
                {System.out.println("Do widzenia!");
					break;}

				System.out.println();


			} catch (Exception e) {
//				e.printStackTrace();
				errorTrigger = true;
//				sc.next();
			}



		} while (mode != OperationMode.EXIT || errorTrigger == true);
		
		sc.close();
		
	}

}
