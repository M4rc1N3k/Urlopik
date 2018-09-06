package test;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateTest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate a = LocalDate.now();
		System.out.println(a);
		
		LocalDate b = a.plusDays(13);
		System.out.println(b);
		
		DayOfWeek c = b.getDayOfWeek();
		c.toString();
		System.out.println(c);
		
		System.out.println(a.compareTo(b));
		System.out.println(b.compareTo(a));
		
		
	}

}
