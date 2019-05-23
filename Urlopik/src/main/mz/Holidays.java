import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Holidays {

	private List<LocalDate> holidaysList;
	private int year;

	public Holidays(int year){
		this.year = year;
		this.holidaysList = generateHolidaysList();
	}

	public List<LocalDate> getHolidaysList() {
		return generateHolidaysList();
	}

	private List<LocalDate> generateHolidaysList(){
		
		List<LocalDate> holidaysList = new ArrayList<>();
		String yearString = String.valueOf(year);
		
		holidaysList.add(LocalDate.parse(yearString+"-01-01"));		//New Year
		holidaysList.add(LocalDate.parse(yearString+"-01-06"));		//Epiphany (Three Kings' Day)
		holidaysList.add(LocalDate.parse(yearString+"-05-01"));		//Labour Day
		holidaysList.add(LocalDate.parse(yearString+"-05-03"));		//Constitution Day
		holidaysList.add(LocalDate.parse(yearString+"-08-15"));		//Armed Forces Day
		holidaysList.add(LocalDate.parse(yearString+"-11-01"));		//All Saints' Day
		holidaysList.add(LocalDate.parse(yearString+"-11-11"));		//Independence Day
		holidaysList.add(LocalDate.parse(yearString+"-12-25"));		//Christmas
		holidaysList.add(LocalDate.parse(yearString+"-12-26"));		//Christmas (2nd day)
		
		
		LocalDate easter;

		easter = easterCalculator();
		
		holidaysList.add(easter);								//Easter
		holidaysList.add(easter.plusDays(1));					//Easter Monday
		holidaysList.add(easter.plusDays(49));					//Pentecostal Sunday
		holidaysList.add(easter.plusDays(60));					//Corpus Christi

		
		return holidaysList;
	}
	
	public LocalDate easterCalculator () {
		
		int a,b,c,h,l,m,n,day,month;
		String easterSt;
		LocalDate easter;
		
		a = year%19;
		b = year/100;
		c = year%100;
		
		h = (19*a+b-b/4-(b+1-(b+8)/25)/3+15)%30;
		l = (32+2*(b%4+c/4)-h-c%4)%7;
		m = (a+11*h+22*l)/451;
		n = h+l-7*m+114;
		
		day = 1+n%31;
		
		if (n/31 == 3) {
			month = 3;
		} else {
			month = 4;
		}
		
		if (day/10 > 0) {
			easterSt = year+"-0"+month+"-"+day;
		} else {
			easterSt = year+"-0"+month+"-0"+day;
		}
		
		easter = LocalDate.parse(easterSt);
		
		
		return easter;
	}

	public boolean isOnList(LocalDate date){
		if (holidaysList.contains(date))
		{
			return true;
		}
		return false;
	}
}
