package calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public abstract class Calendar {
    protected DayOfWeek firstDayWeek;
    protected DayOfWeek lastDayOfWeek;
    protected LocalDate date;
    protected Set<DayOfWeek> weekends = new HashSet<DayOfWeek>() {{
        add(DayOfWeek.SATURDAY);
        add(DayOfWeek.SUNDAY);
    }};

    public String generateCalendar() {
        LocalDate tempDate = getFirstDayOfMonth(date);
        String out = addHeader() +
                addStartWeek() +
                addDaysNames() +
                addEndWeek() +
                doAlign();

        while (!isEndOfMonth(tempDate)) {
            out += printDay(tempDate);
            if (tempDate.getDayOfWeek().equals(lastDayOfWeek))
                out += addEndWeek() + addStartWeek();

            tempDate = tempDate.plusDays(1);
        }
        return out;
    }

    private boolean isEndOfMonth(LocalDate tempDate) {
        return tempDate.equals(getLastDayOfMonth(date).plusDays(1));
    }

    private String printDay(LocalDate tempDate) {
        String dayResult;
        if (tempDate.equals(LocalDate.now()))
            dayResult = addCurrentDay(tempDate.getDayOfMonth());
        else {
            if (weekends.contains(tempDate.getDayOfWeek()))
                dayResult = addWeekend(tempDate.getDayOfMonth());
            else
                dayResult = addWorkDay(tempDate.getDayOfMonth());
        }
        return dayResult;
    }

    public void setDate(LocalDate newDate) {
        this.date = newDate;
    }

    public void setWeekends(Set<DayOfWeek> weekends) {
        this.weekends.addAll(weekends);
    }

    public void setLastDayOfWeek(DayOfWeek lastDayOfWeek) {
        this.lastDayOfWeek = lastDayOfWeek;
    }

    protected abstract String addHeader();

    protected abstract String addDaysNames();

    protected abstract String doAlign();

    protected abstract String addStartWeek();

    protected abstract String addEndWeek();

    protected abstract String addWorkDay(int dayNumber);

    protected abstract String addWeekend(int dayNumber);

    protected abstract String addCurrentDay(int dayNumber);

    protected LocalDate getFirstDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(1);
    }

    protected LocalDate getLastDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(date.lengthOfMonth());
    }

    protected LocalDate сopyDate(LocalDate obj) {
        return obj.withDayOfMonth(obj.getDayOfMonth());
    }

}



