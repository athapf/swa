package de.thaso.swa.fe.bean.workshop;

import javax.enterprise.inject.Any;
import java.util.Date;

@Any
public class WorkshopModel {

    private String title;
    private Integer number;
    private Date dayOfEvent;
    private Integer attendance;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDayOfEvent() {
        return dayOfEvent;
    }

    public void setDayOfEvent(Date dayOfEvent) {
        this.dayOfEvent = dayOfEvent;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }
}
