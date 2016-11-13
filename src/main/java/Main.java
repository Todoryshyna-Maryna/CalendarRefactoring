import calendar.Calendar;
import calendar.ConsoleCalendar;
import calendar.HtmlCalendar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        Set<DayOfWeek> weekends = new HashSet<DayOfWeek>();
        weekends.add(DayOfWeek.WEDNESDAY);
        weekends.add(DayOfWeek.THURSDAY);

        int request = 0;
        if (request == 1) {
            Calendar calendar = new ConsoleCalendar();
//            calendar.setDate();
            System.out.println(calendar.generateCalendar());
        } else {
            Calendar calendar = new HtmlCalendar(weekends);
            calendar.setDate(LocalDate.of(2016, 11, 2));
            FileWriter fw = new FileWriter(new File("html_calendar.html"));
            fw.append(calendar.generateCalendar());
            fw.flush();
            fw.close();
        }
    }
}
