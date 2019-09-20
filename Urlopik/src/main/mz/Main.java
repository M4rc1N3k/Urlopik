import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.lang.math.NumberUtils;

import java.io.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IllegalArgumentException {

        int curYear = LocalDate.now().getYear();
        int nextYear = LocalDate.now().getYear() + 1;

        File file = new File("off.json");
        String offBinary = readFromFile(file);

        Counter curCount;
        int plannedOffCount;
        int usedOffCount;
        int remainingOffCount;
        int totalOffCount = 90;

        OperationMode mode;

        IMapper mapper = new Deserializer(offBinary,curYear);
        IMap map = mapper.getYearMap();
        Map<LocalDate, OffMode> curYearMap = map.getMap();

        Scanner sc = new Scanner(System.in);

        System.out.println("Witaj w programie ===Urlopik===");
        System.out.println("©2018 by MZ\n");

        do {
            curCount = new Counter(curYearMap, totalOffCount);
            plannedOffCount = curCount.getPlanned();
            usedOffCount = curCount.getUsed();
            remainingOffCount = curCount.getRemaining();

            System.out.println("\nTwoje obecnie zaplanowane okresy urlopu to:");

            System.out.println(new Printer(curYearMap).getOffPeriodsJuxtaposition());

            System.out.println();
            summary(totalOffCount, plannedOffCount, usedOffCount, remainingOffCount);

            while (true) {

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
                        break;
                    }
                    else if (mode == OperationMode.EXIT) {

                        ISerializer serializer = new Serializer();
                        serializer.serialize(curYearMap, file);

                        ISerializer csvCreator = new CsvCreator();
                        csvCreator.serialize(curYearMap, new File("off.csv"));

                        ISerializer mapDtoCreator = new MapDtoCreator();
                        mapDtoCreator.serialize(curYearMap,new MapDto());

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

    private static String readFromFile(File file) {
        String offBinary = null;

        try {
            offBinary = Files.asCharSource(file, Charsets.UTF_8).read();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return offBinary;
    }

    static void summary(int total, int planed, int used, int remaining) {
        System.out.println("Ogółem urlopu " + total + " dni, w tym:");
        System.out.println("\t-zaplanowano " + planed + " dni");
        System.out.println("\t-wykorzystano " + used + " dni");
        System.out.println("\t-pozostało " + remaining + " dni");
    }


}
