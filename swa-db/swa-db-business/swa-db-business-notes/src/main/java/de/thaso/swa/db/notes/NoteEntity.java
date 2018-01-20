package de.thaso.swa.db.notes;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "T_NOTE")
@NamedQueries({
        @NamedQuery(name = NoteEntity.FIND_BY_NUMBER, query = "select n from NoteEntity n where n.number = :number"),
        @NamedQuery(name = NoteEntity.FIND_BY_TITLE, query = "select n from NoteEntity n where n.title like :title order by n.dayOfEvent asc"),
})
public class NoteEntity extends EntityBase {

    private static final long serialVersionUID = 3365937645927953132L;

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
}
