package de.thaso.fum.web.ra.impl;

import de.thaso.fum.web.ra.api.FumConnection;
import de.thaso.fum.web.ra.api.FumWebLoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FumConnectionImpl implements FumConnection {
    private static final Logger LOG = LoggerFactory.getLogger(FumConnectionImpl.class);

    private FumManagedConnection managedConnection;
//    private FumManagedConnectionFactory managedConnectionFactory;
//
//    public FumConnectionImpl(final FumManagedConnection managedConnection, final FumManagedConnectionFactory managedConnectionFactory) {
//        this.managedConnection = managedConnection;
//        this.managedConnectionFactory = managedConnectionFactory;
//    }

    public FumConnectionImpl(final FumManagedConnection managedConnection) {
        this.managedConnection = managedConnection;
    }

    public FumManagedConnection getManagedConnection() {
        return managedConnection;
    }

    public void setManagedConnection(FumManagedConnection managedConnection) {
        this.managedConnection = managedConnection;
    }

    @Override
    public FumWebLoginUser findLoginUser(String loginName) {
        LOG.debug("findLoginUser");
        return null;
    }

    @Override
    public Long storeLoginUser(FumWebLoginUser loginUser) {
        LOG.debug("storeLoginUser");
        return null;
    }

    @Override
    public void close() {
        LOG.debug("close");
    }
}
