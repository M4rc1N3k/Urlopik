package mz;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {


        int curYear = LocalDate.now().getYear();
        int nextYear = LocalDate.now().getYear() + 1;

        File file = new File("off_forCancel.txt");
        String offBinary = null;
        try {
            offBinary = Files.toString(file, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        int totalOffCount = 90;

        OperationMode mode = OperationMode.DEFAULT;
        Boolean errorTrigger;

        Map<LocalDate, OffMode> curYearMap = Mapper.generateMap(offBinary, curYear);

        Counter curCount = new Counter(curYearMap, totalOffCount);
        int plannedOffCount = curCount.calcPlanned();
        int usedOffCount = curCount.calcPlanned();
        int remainingOffCount = curCount.calcRemaining();

        Scanner sc = new Scanner(System.in);

        System.out.println("Witaj w programie ===Urlopik===");
        System.out.println("©2018 by MZ\n");

        do {
            errorTrigger = false;
            try {

                curCount = new Counter(curYearMap, totalOffCount);
                plannedOffCount = curCount.calcPlanned();
                usedOffCount = curCount.calcUsed();
                remainingOffCount = curCount.calcRemaining();

                System.out.println("\nTwoje obecnie zaplanowane okresy urlopu to:");
                Printer.offPeriodsDisplay(curYearMap);

                System.out.println();
                summary(totalOffCount, plannedOffCount, usedOffCount, remainingOffCount );


                System.out.println("\nCo chciałbyś zrobić?");
                System.out.println("\t1) dopisać urlop");
                System.out.println("\t2) anulować urlop");
                System.out.println("\t3) zakończyć program");

                mode = OperationMode.fromInt(Integer.parseInt(sc.next()));

                if (mode == OperationMode.SET || mode == OperationMode.CANCEL) {
                    curYearMap = Modifier.changeOff(mode, remainingOffCount, plannedOffCount, curYearMap);
                    //TODO: saving map to the output file
                } else if (mode == OperationMode.EXIT) {
                    System.out.println("Do widzenia!");
                    break;
                }

                System.out.println();


            } catch (Exception e) {
//				e.printStackTrace();
                errorTrigger = true;
//				sc.next();
            }


        } while (mode != OperationMode.EXIT || errorTrigger == true);

        sc.close();

    }

    static void summary(int total, int planed, int used, int remaining) {
        System.out.println("Ogółem urlopu " + total + " dni, w tym:");
        System.out.println("\t-zaplanowano " + planed + " dni");
        System.out.println("\t-wykorzystano " + used + " dni");
        System.out.println("\t-pozostało " + remaining + " dni");
    }

}
