import java.time.LocalDate;
import java.util.*;


public class Mapper implements IMapper {

	private String offBinary;
	private int year;

	public Mapper(String offBinary, int year) {
		this.offBinary = offBinary;
		this.year = year;
	}
    public IMap getYearMap() {
		return new OffMap(generateYearMap(offBinary,year));
	}

	private Map<LocalDate, OffMode> generateYearMap(String offBinary, int year) {
		int a = offBinary.length();
		char array[] = offBinary.toCharArray();
		Map<LocalDate, OffMode> yearMap = new TreeMap<>(	);
		List<Integer> listInt = new ArrayList<Integer>();


		for (int i = 0; i < a; i++) {
			int candidate = Character.getNumericValue(array[i]);
			if (candidate == 0 || candidate == 1 || candidate == 2) //0-working, 1-off, 2-holiday
				listInt.add(candidate);
		}

		int leapYearAppendix = isLeapYear(year) ? 1 : 0;
		int yearLength = 365 + leapYearAppendix;

		List<LocalDate> holidaysList = new Holidays(String.valueOf(year)).getHolidaysList();


		LocalDate date = LocalDate.parse(String.valueOf(year + "-01-01"));

		for (int i = 0; i < yearLength; i++) {
			if (isHoliday(date, holidaysList))
				yearMap.put(date, OffMode.HOLIDAY);
			else
				yearMap.put(date, OffMode.fromInt(listInt.get(i)));
			date = date.plusDays(1);
		}

		//TODO: mapping the following year

		return yearMap;
	}

	private Boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	private Boolean isHoliday(LocalDate date, List<LocalDate> holidaysList) {

		for (LocalDate holiday : holidaysList) {
			if (holiday.compareTo(date) == 0) {
				return Boolean.TRUE;
			}
		}

		if (date.getDayOfWeek().toString() == "SATURDAY" || date.getDayOfWeek().toString() == "SUNDAY") {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

}