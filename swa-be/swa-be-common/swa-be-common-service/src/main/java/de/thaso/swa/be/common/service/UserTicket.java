package de.thaso.swa.be.common.service;

/**
 * UserTicket
 *
 * @author thaler
 * @since 2017-05-29
 */
public class UserTicket extends DataBase {

    private static final long serialVersionUID = -7083922341437184218L;

    private String userId;

    public UserTicket(final String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final UserTicket that = (UserTicket) o;

        return userId != null ? userId.equals(that.userId) : that.userId == null;
    }

    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0;
    }
}
