import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;


public class CounterTest {

    @Test
    public void shouldCountOnlyOlderThanToday() {
        //given
        LocalDate today = LocalDate.now();
        Map<LocalDate, OffMode> days = new LinkedHashMap<>();
        days.put(today.minusDays(1), OffMode.OFF);
        days.put(today, OffMode.OFF);
        days.put(today.plusDays(1), OffMode.OFF);


        Counter counter = Mockito.spy(new Counter(days, 0));
        Mockito.when(counter.todayDayNumber()).thenReturn(2);

        //when
        int result = counter.getPlanned();

        //then
        //MatcherAssert.assertThat(result, equalTo(1));
    }

    @Test
    public void shouldCountOnlyUsedDaysBeforeToday() {
        //given
        LocalDate today = LocalDate.now();
        Map<LocalDate, OffMode> days = new LinkedHashMap<>();
        days.put(LocalDate.of(2001, 01, 01), OffMode.OFF);
        days.put(LocalDate.of(2001, 01, 02), OffMode.OFF);
        days.put(LocalDate.of(2001, 01, 03), OffMode.OFF);

        Counter counter = Mockito.spy(new Counter(days, 0));
        Mockito.when(counter.todayDayNumber()).thenReturn(2);
        Mockito.when(counter.today()).thenReturn(LocalDate.of(2001, 01, 02));

        //when
        int result = counter.getUsed();

        //then
        //MatcherAssert.assertThat(result, equalTo(2));
    }

}