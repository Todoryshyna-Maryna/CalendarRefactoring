package calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Set;

public class ConsoleCalendar extends Calendar {
    String WEEKEND_COLOR = "\033[31m";
    String CURRENT_DAY_COLOR = "\33[32;1m";
    String DEFAULT_COLOR = "\33[30;1m";
    String HEADER_COLOR = "\33[34;1m";


    public ConsoleCalendar(DayOfWeek firstDayOfWeek) {
        this.date = LocalDate.now();
        this.firstDayWeek = firstDayOfWeek;
        this.lastDayOfWeek = calculateLastDayOfWeek();
    }

    public ConsoleCalendar() {
        this(DayOfWeek.MONDAY);
    }

    public ConsoleCalendar(Set<DayOfWeek> weekends) {
        this();
        this.weekends.clear();
        this.weekends.addAll(weekends);
    }

    private DayOfWeek calculateLastDayOfWeek() {
        return firstDayWeek.minus(1);
    }

    protected String addHeader() {
        String result = HEADER_COLOR;
        result += date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "\t";
        result += date.getYear() + DEFAULT_COLOR;
        result += "\n";
        return result;
    }

    protected String addDaysNames() {
        String result = "";
        DayOfWeek dayOfWeek = DayOfWeek.of(firstDayWeek.getValue());
        do {
            result += dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "\t";
            dayOfWeek = dayOfWeek.plus(1);
        } while (!dayOfWeek.equals(lastDayOfWeek));

        result += dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "\t";

        return result;
    }

    protected String doAlign() {
        String result = "";
        DayOfWeek dayOfWeek = getFirstDayOfMonth(date).getDayOfWeek();
        while (!dayOfWeek.equals(firstDayWeek)) {
            result += "\t";
            dayOfWeek = dayOfWeek.minus(1);
        }
        return result;
    }

    protected String addStartWeek() {
        return "";
    }

    protected String addEndWeek() {
        return "\n";
    }

    protected String addWorkDay(int dayNumber) {
        String result = "";
        return result += dayNumber + "\t";
    }

    protected String addWeekend(int dayNumber) {
        String result = "";
        for (int day = 1; day <= 7; day++) {
            if (weekends.contains(DayOfWeek.of(day))) {
                result += WEEKEND_COLOR;
            }
        }
        return result += dayNumber + DEFAULT_COLOR + "\t";
    }

    protected String addCurrentDay(int dayNumber) {
        String result = "";
        if (LocalDate.now().getDayOfMonth() == dayNumber) {
            result += CURRENT_DAY_COLOR;
        }
        return result += dayNumber + DEFAULT_COLOR + "\t";
    }
}
