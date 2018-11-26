package mz;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.lang.math.NumberUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IllegalArgumentException {

        int curYear = LocalDate.now().getYear();
        int nextYear = LocalDate.now().getYear() + 1;

        File file = new File("off_forCancel.txt");
        String offBinary = null;
        try {
            offBinary = Files.asCharSource(file, Charsets.UTF_8).read();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        int totalOffCount = 90;

        OperationMode mode = OperationMode.DEFAULT;

        Map<LocalDate, OffMode> curYearMap = new Mapper(offBinary, curYear).getYearMap();

        List<String[]> curOffPeriodsJuxtaposition;

        Counter curCount = new Counter(curYearMap, totalOffCount);
        int plannedOffCount = curCount.calcPlanned();
        int usedOffCount = curCount.calcPlanned();
        int remainingOffCount = curCount.calcRemaining();

        Scanner sc = new Scanner(System.in);

        System.out.println("Witaj w programie ===Urlopik===");
        System.out.println("©2018 by MZ\n");

        do {
                curCount = new Counter(curYearMap, totalOffCount);
                plannedOffCount = curCount.calcPlanned();
                usedOffCount = curCount.calcUsed();
                remainingOffCount = curCount.calcRemaining();

                System.out.println("\nTwoje obecnie zaplanowane okresy urlopu to:");

                curOffPeriodsJuxtaposition  = new Printer(curYearMap).getOffPeriodsJuxtaposition();

                System.out.println();
                summary(totalOffCount, plannedOffCount, usedOffCount, remainingOffCount );

            while(true) {

                    System.out.println("\nCo chciałbyś zrobić?");
                    System.out.println("\t1) dopisać urlop");
                    System.out.println("\t2) anulować urlop");
                    System.out.println("\t3) zakończyć program");

                    String readMode = sc.next();

                try {
                    if (!NumberUtils.isNumber(readMode)) throw new IllegalArgumentException();
                    if (Integer.parseInt(readMode) / 4 != 0) throw new IllegalArgumentException();
                    mode = OperationMode.fromInt(Integer.parseInt(readMode));


                    if (mode == OperationMode.SET || mode == OperationMode.CANCEL) {
                        curYearMap = new Modifier(mode, remainingOffCount, plannedOffCount, curYearMap).getModifiedYearMap();
                        //TODO: saving map to the output file
                        break;
                    } else if (mode == OperationMode.EXIT) {
                        System.out.println("Do widzenia!");
                        break;
                    }

                    System.out.println();


                } catch (IllegalArgumentException e) {

                    System.out.println("Błędny wybór trybu! Wybierz jeszcze raz");
                }
            }

        } while (mode != OperationMode.EXIT);

        sc.close();

    }

    static void summary(int total, int planed, int used, int remaining) {
        System.out.println("Ogółem urlopu " + total + " dni, w tym:");
        System.out.println("\t-zaplanowano " + planed + " dni");
        System.out.println("\t-wykorzystano " + used + " dni");
        System.out.println("\t-pozostało " + remaining + " dni");
    }

}
