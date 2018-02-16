package de.thaso.swa.be.workshop;

import java.io.Serializable;
import java.util.Date;

/**
 * WorkshopData
 *
 * @author thaler
 * @since 21.09.16
 */
public class WorkshopData implements Serializable {

    private static final long serialVersionUID = -7049489388682590929L;

    private Long id;
    private String title;
    private Integer number;
    private String content;
    private Integer duration;
    private Date dayOfEvent;
    private Integer attendance;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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
