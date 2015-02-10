package ws.fcdigital.itsapp_v0;

/**
 * Created by André on 2/5/2015.
 */
public class EventItsApp {
    private String event_name;
    private String event_local;
    private String event_type;
    private String event_day;
    private String event_month;
    private String event_hour;
    private int event_picture;

    public EventItsApp(String event_name, String event_local, String event_type, String event_day, String event_month, String event_hour, int event_picture) {
        super();
        this.event_name = event_name;
        this.event_local = event_local;
        this.event_type = event_type;
        this.event_day = event_day;
        this.event_month = event_month;
        this.event_hour = event_hour;
        this.event_picture = event_picture;
    }

    public String getName() {
        return event_name;
    }

    public String getLocal() {
        return event_local;
    }

    public String getType() {
        return event_type;
    }

    public String getDay() {
        return event_day;
    }

    public String getMonth() {
        return event_month;
    }

    public String getHour() {
        return event_hour;
    }

    public int getPicture() {
        return event_picture;
    }

    public void setName(String name) {
        this.event_name = event_name;
    }

    public void setLocal(String local) {
        this.event_local = event_local;
    }

    public void setType(String type) {
        this.event_type = event_type;
    }

    public void setDay(String day) {
        this.event_day = event_day;
    }

    public void setMonth(String month) {
        this.event_month = event_month;
    }

    public void setHour(String hour) {
        this.event_hour = event_hour;
    }

    public void setPicture(int picture) {
        this.event_picture = event_picture;
    }
}
