package calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Set;

public class HtmlCalendar extends Calendar {
    public HtmlCalendar(DayOfWeek firstDayOfWeek) {
        this.date = LocalDate.now();
        this.firstDayWeek = firstDayOfWeek;
        this.lastDayOfWeek = calculateLastDayOfWeek();
    }

    public HtmlCalendar() {
        this(DayOfWeek.MONDAY);
    }

    public HtmlCalendar(Set<DayOfWeek> weekends) {
        this();
        this.weekends.clear();
        this.weekends.addAll(weekends);
    }

    private DayOfWeek calculateLastDayOfWeek() {
        return firstDayWeek.minus(1);
    }

    protected String addHeader() {
        String result = "<div style=\"color: blue\">" + date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " ";
        result += date.getYear() + "</div>";
        result += "<table>";
        return result;
    }

    protected String addDaysNames() {
        String result = "";
        DayOfWeek dayOfWeek = DayOfWeek.of(firstDayWeek.getValue());
        do {
            result += "<td>" + dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "</td>";
            dayOfWeek = dayOfWeek.plus(1);
        } while (!dayOfWeek.equals(lastDayOfWeek));

        result += "<td>" + dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "</td>";

        return result;
    }

    protected String doAlign() {
        String result = "";
        DayOfWeek dayOfWeek = getFirstDayOfMonth(date).getDayOfWeek();
        while (!dayOfWeek.equals(firstDayWeek)) {
            result += "<td></td>";
            dayOfWeek = dayOfWeek.minus(1);
        }
        return result;
    }

    protected String addStartWeek() {
        return "<tr>";
    }

    protected String addEndWeek() {
        return "</tr>";
    }

    protected String addWorkDay(int dayNumber) {
        return "<td>" + dayNumber + "</td>";
    }

    protected String addWeekend(int dayNumber) {
        return "<td style=\"color: red\">" + dayNumber + "</td>";
    }

    protected String addCurrentDay(int dayNumber) {
        return "<td style=\"color: blue\">" + dayNumber + "</td>";
    }
}
