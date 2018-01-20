package de.thaso.swa.db.workshop.cart;

import de.thaso.swa.db.common.base.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * WorkshopEntity
 *
 * @author thaler
 * @since 2017-12-11
 */
@Entity
@Table(name = "T_WORKSHOP")
@NamedQueries({
        @NamedQuery(name = WorkshopEntity.FIND_BY_NUMBER, query = "select w from WorkshopEntity w where w.number = :number"),
        @NamedQuery(name = WorkshopEntity.FIND_BY_TITLE, query = "select w from WorkshopEntity w where w.title like :title order by w.dayOfEvent asc"),
})
public class WorkshopEntity extends EntityBase {

    private static final long serialVersionUID = 3383206127867057506L;

    public static final String FIND_BY_NUMBER = "FIND_BY_NUMBER";
    public static final String FIND_BY_TITLE = "FIND_BY_TITLE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WorkshopSequence")
    @SequenceGenerator(name = "WorkshopSequence", sequenceName = "SEQ_ID_WORKSHOP", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    @Size(min=5, max=80)
    @NotNull
    private String title;

    @Column(name = "NUMBER")
    @NotNull
    private Integer number;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "DURATION")
    private Integer duration;

    @Temporal(TemporalType.DATE)
    @Column(name = "DAY_OF_EVENT")
    private Date dayOfEvent;

    @Column(name = "ATTENDANCE")
    private Integer attendance;

    @Override
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
