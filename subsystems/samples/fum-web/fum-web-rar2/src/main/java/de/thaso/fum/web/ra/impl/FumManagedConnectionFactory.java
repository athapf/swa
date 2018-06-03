package de.thaso.fum.web.ra.impl;

import de.thaso.fum.web.ra.api.FumConnection;
import de.thaso.fum.web.ra.api.FumConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Reference;
import javax.resource.Referenceable;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionDefinition;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterAssociation;
import javax.security.auth.Subject;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@ConnectionDefinition(connectionFactory = FumConnectionFactory.class,
   connectionFactoryImpl = FumConnectionFactoryImpl.class,
   connection = FumConnection.class,
   connectionImpl = FumConnectionImpl.class)
public class FumManagedConnectionFactory implements ManagedConnectionFactory, ResourceAdapterAssociation, Serializable, Referenceable {
    private static final long serialVersionUID = -6745055932400061627L;
    private static final Logger LOG = LoggerFactory.getLogger(FumManagedConnectionFactory.class);

    private Reference reference;
    private ResourceAdapter resourceAdapter;
    private PrintWriter printWriter;

    public FumManagedConnectionFactory() {
        this.resourceAdapter = null;
        this.printWriter = null;
    }

    @Override
    public Object createConnectionFactory(final ConnectionManager connectionManager) throws ResourceException {
        return new FumConnectionFactoryImpl(this, connectionManager);
    }

    @Override
    public Object createConnectionFactory() throws ResourceException {
        throw new ResourceException("This resource adapter doesn't support non-managed environments");
    }

    @Override
    public ManagedConnection createManagedConnection(final Subject subject, final ConnectionRequestInfo connectionRequestInfo) throws ResourceException {
//        return new FumManagedConnection(this);
        return new FumManagedConnection();
    }

    @Override
    public ManagedConnection matchManagedConnections(final Set set, final Subject subject, final ConnectionRequestInfo connectionRequestInfo) throws ResourceException {
        ManagedConnection result = null;

        final Iterator it = set.iterator();
        while (result == null && it.hasNext()) {
            ManagedConnection managedConnection = (ManagedConnection)it.next();
            if (managedConnection instanceof FumManagedConnection) {
                FumManagedConnection fumManagedConnection = (FumManagedConnection)managedConnection;
                result = fumManagedConnection;
            }
        }
        return result;
    }

    @Override
    public void setLogWriter(PrintWriter printWriter) throws ResourceException {
        this.printWriter = printWriter;
    }

    @Override
    public PrintWriter getLogWriter() throws ResourceException {
        return printWriter;
    }

    @Override
    public ResourceAdapter getResourceAdapter() {
        return resourceAdapter;
    }

    @Override
    public void setResourceAdapter(ResourceAdapter resourceAdapter) throws ResourceException {
        this.resourceAdapter = resourceAdapter;
    }

    @Override
    public Reference getReference() {
        return reference;
    }

    @Override
    public void setReference(Reference reference) {
        this.reference = reference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FumManagedConnectionFactory that = (FumManagedConnectionFactory) o;
        return Objects.equals(resourceAdapter, that.resourceAdapter) &&
                Objects.equals(printWriter, that.printWriter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceAdapter, printWriter);
    }
}
