package de.thaso.fum.web.ra.impl;

import de.thaso.fum.web.ra.api.FumConnection;
import de.thaso.fum.web.ra.api.FumWebLoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEvent;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FumManagedConnection implements ManagedConnection {
    private static final Logger LOG = LoggerFactory.getLogger(FumManagedConnection.class);

    private PrintWriter printWriter;
    //private FumManagedConnectionFactory managedConnectionFactory;
    private List<ConnectionEventListener> listeners;
    private Object connection;

    public FumManagedConnection() {//final FumManagedConnectionFactory managedConnectionFactory) {
      //this.managedConnectionFactory = managedConnectionFactory;
      this.printWriter = null;
      this.listeners = new ArrayList<ConnectionEventListener>(1);
      this.connection = null;
    }

    @Override
    public Object getConnection(final Subject subject, final ConnectionRequestInfo connectionRequestInfo) throws ResourceException {
        connection = new FumConnectionImpl(this);// managedConnectionFactory);
        return connection;
    }

    @Override
    public void destroy() throws ResourceException {
        this.connection = null;
    }

    @Override
    public void cleanup() throws ResourceException {

    }

    @Override
    public void associateConnection(final Object connection) throws ResourceException {
        this.connection = connection;
    }

    @Override
    public void addConnectionEventListener(final ConnectionEventListener connectionEventListener) {
        if (connectionEventListener == null) {
            throw new IllegalArgumentException("Listener is null");
        }
        listeners.add(connectionEventListener);
    }

    @Override
    public void removeConnectionEventListener(final ConnectionEventListener connectionEventListener) {
        if (connectionEventListener == null) {
            throw new IllegalArgumentException("Listener is null");
        }
        listeners.remove(connectionEventListener);
    }

    @Override
    public XAResource getXAResource() throws ResourceException {
        throw new NotSupportedException("GetXAResource not supported");
    }

    @Override
    public LocalTransaction getLocalTransaction() throws ResourceException {
        throw new NotSupportedException("LocalTransaction not supported");
    }

    @Override
    public void setLogWriter(final PrintWriter printWriter) throws ResourceException {
        this.printWriter = printWriter;
    }

    @Override
    public PrintWriter getLogWriter() throws ResourceException {
        return printWriter;
    }

    public ManagedConnectionMetaData getMetaData() throws ResourceException {
        return new FumManagedConnectionMetaData();
    }

    FumWebLoginUser findLoginUser(final String loginName) {
        return null;
    }

    Long storeLoginUser(final FumWebLoginUser loginUser) {
        return null;
    }

    void closeHandle(final FumConnection handle) {
        ConnectionEvent event = new ConnectionEvent(this, ConnectionEvent.CONNECTION_CLOSED);
        event.setConnectionHandle(handle);

        for (ConnectionEventListener connectionEventListener : listeners) {
            connectionEventListener.connectionClosed(event);
        }
    }
}
