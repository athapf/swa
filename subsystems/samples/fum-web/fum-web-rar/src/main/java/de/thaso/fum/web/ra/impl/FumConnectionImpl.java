package de.thaso.fum.web.ra.impl;

import de.thaso.fum.web.ra.api.FumConnection;
import de.thaso.fum.web.ra.api.FumWebLoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.resource.ResourceException;
import javax.resource.cci.ConnectionMetaData;
import javax.resource.cci.Interaction;
import javax.resource.cci.LocalTransaction;
import javax.resource.cci.ResultSetInfo;

public class FumConnectionImpl implements FumConnection {
    private static final Logger LOG = LoggerFactory.getLogger(FumConnectionImpl.class);

    private FumManagedConnection managedConnection;
    private FumManagedConnectionFactory managedConnectionFactory;

    public FumConnectionImpl(final FumManagedConnection managedConnection, final FumManagedConnectionFactory managedConnectionFactory) {
        this.managedConnection = managedConnection;
        this.managedConnectionFactory = managedConnectionFactory;
    }

    public FumManagedConnection getManagedConnection() {
        return managedConnection;
    }

    public void setManagedConnection(FumManagedConnection managedConnection) {
        this.managedConnection = managedConnection;
    }

    public void setManagedConnectionFactory(FumManagedConnectionFactory managedConnectionFactory) {
        this.managedConnectionFactory = managedConnectionFactory;
    }

    @Override
    public FumWebLoginUser findLoginUser(String loginName) {
        LOG.debug("findLoginUser");
        FumWebLoginUser loginUser = new FumWebLoginUser();
        loginUser.setLoginName("horst");
        return loginUser;
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

    @Override
    public Interaction createInteraction() throws ResourceException {
        return null;
    }

    @Override
    public LocalTransaction getLocalTransaction() throws ResourceException {
        return null;
    }

    @Override
    public ConnectionMetaData getMetaData() throws ResourceException {
        return null;
    }

    @Override
    public ResultSetInfo getResultSetInfo() throws ResourceException {
        return null;
    }
}
