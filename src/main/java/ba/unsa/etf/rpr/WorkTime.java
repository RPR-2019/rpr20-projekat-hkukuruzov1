package ba.unsa.etf.rpr;

public class WorkTime {
    private String start;
    private String end;
    private String id;
    private WorkPlace typeOfWork;
    private int day;
    private String month;
    private int year;

    public WorkTime(String start, String end, String id, WorkPlace typeOfWork, int day, String month, int year) {
        this.start = start;
        this.end = end;
        this.id = id;
        this.typeOfWork = typeOfWork;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public WorkTime() {
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public String getStart() {
        return start;
    }

    public WorkPlace getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(WorkPlace typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
