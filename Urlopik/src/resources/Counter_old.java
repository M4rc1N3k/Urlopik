package mz;

import java.time.LocalDate;
import java.util.Map;

public class Counter_old {

    private Map<LocalDate, OffMode> yearMap;
    private int total, planned, remaining, used;


    public Counter_old(Map<LocalDate, OffMode> yearMap, int total) {
        this.yearMap = yearMap;
        this.total = total;
        this.planned = calcPlanned();
        this.remaining = calcRemaining();
        this.used = calcUsed();
    }

    LocalDate today() {
        return LocalDate.now();
    }

    int todayDayNumber() {
        return today().getDayOfYear();
    }


    public int calcPlanned() {

        int planned = 0;
        LocalDate day = today();

        int iterator = todayDayNumber();
        while (iterator < yearMap.size()) {

            if (yearMap.get(day) == OffMode.OFF) {
                planned++;
            }

            day = day.plusDays(1);
            iterator++;
        }

        return planned;
    }

    public int calcUsed() {
        LocalDate today = today();
        int todayNumber = todayDayNumber();
        int used = 0;
        LocalDate day = LocalDate.parse(String.valueOf(today.getYear() + "-01-01"));

        int iterator = 1;
        while (iterator <= todayNumber) {
            if (yearMap.get(day) == OffMode.OFF) {
                used++;
            }
            day = day.plusDays(1);
            iterator++;
        }

        return used;
    }

    public int calcRemaining() {
        int remaining = total - calcUsed() - calcPlanned();

        return remaining;
    }

}
