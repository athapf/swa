package de.thaso.swa.be.common.service;

/**
 * ContextData
 *
 * @author thaler
 * @since 2017-05-29
 */
public class ContextData extends DataBase {

    private static final long serialVersionUID = -8192761753282284159L;

    private UserTicket userTicket;

    public ContextData(final UserTicket userTicket) {
        this.userTicket = userTicket;
    }

    public UserTicket getUserTicket() {
        return userTicket;
    }

    public void setUserTicket(final UserTicket userTicket) {
        this.userTicket = userTicket;
    }
}
