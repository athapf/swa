package de.thaso.fum.web.ra.impl;

import de.thaso.fum.web.ra.api.FumConnection;
import de.thaso.fum.web.ra.api.FumConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;

public class FumConnectionFactoryImpl implements FumConnectionFactory {
    private static final long serialVersionUID = -6269002159074675196L;

    private static final Logger LOG = LoggerFactory.getLogger(FumConnectionFactoryImpl.class);

    private FumManagedConnectionFactory managedConnectionFactory;
    private ConnectionManager connectionManager;
    private Reference reference;

    public FumConnectionFactoryImpl(final FumManagedConnectionFactory managedConnectionFactory, final ConnectionManager connectionManager) {
        this.managedConnectionFactory = managedConnectionFactory;
        this.connectionManager = connectionManager;
    }

    @Override
    public FumConnection getConnection() throws ResourceException {
        return (FumConnection)connectionManager.allocateConnection(managedConnectionFactory, null);
    }

    @Override
    public void setReference(Reference reference) {
        this.reference = reference;
    }

    @Override
    public Reference getReference() throws NamingException {
        return reference;
    }
}