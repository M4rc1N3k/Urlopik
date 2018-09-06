package main;

import java.time.LocalDate;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		int curYear = LocalDate.now().getYear();
		int nextYear = LocalDate.now().getYear()+1;
		
		String offBinary = Reader.dataFromFile("off.txt");
		int totalOffCount = 90;
		
		Map<LocalDate, Integer> curYearMap = Mapper.generateMap(offBinary, curYear);
		System.out.println(curYearMap.entrySet());
		System.out.println(curYearMap.size());
		
		Printer.offPeriodsDisplay(curYearMap);
		
		
		Counter curCount = new Counter(curYearMap, totalOffCount);
		int plannedOffCount = curCount.getPlanned();
		int usedOffCount = curCount.getUsed();
		int remainingOffCount = curCount.getRemaining();
		
		curCount.summary();
		
		curYearMap = Modifier2.changeOff(1, remainingOffCount, plannedOffCount, curYearMap);
		
		
		curCount = new Counter(curYearMap, totalOffCount);
		plannedOffCount = curCount.getPlanned();
		usedOffCount = curCount.getUsed();
		remainingOffCount = curCount.getRemaining();
		
		curCount.summary();


		
		System.out.println();
		
	}

}
