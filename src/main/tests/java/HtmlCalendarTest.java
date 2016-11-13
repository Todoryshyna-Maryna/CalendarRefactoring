import calendar.HtmlCalendar;
import org.junit.Test;

import java.time.DayOfWeek;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

/**
 * Created by Я on 13.11.2016.
 */
public class HtmlCalendarTest {

    @Test
    public void shouldGetDaysNames() {
        HtmlCalendar htmlCalendar = new HtmlCalendar();
        String result = "";
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            result += dayOfWeek + "\t";
        }
        assertThat(result, containsString(htmlCalendar.generateCalendar()));
    }

    @Test
    public void shouldGetDaysNumbers() {

    }

    @Test
    public void shouldSetAlign() {

    }

    @Test
    public void shouldAddWeekend() {

    }

    @Test
    public void shouldVerifyCurrentDay() {

    }
}
