package de.thaso.fum.web.ra.impl;

import javax.resource.ResourceException;
import javax.resource.spi.ManagedConnectionMetaData;

public class FumManagedConnectionMetaData implements ManagedConnectionMetaData {

    public FumManagedConnectionMetaData() {
    }

    @Override
    public String getEISProductName() throws ResourceException {
        return "Fum Resource Adapter";
    }

    @Override
    public String getEISProductVersion() throws ResourceException {
        return "1.0";
    }

    @Override
    public int getMaxConnections() throws ResourceException {
        return 0;
    }

    @Override
    public String getUserName() throws ResourceException {
        return null;
    }
}
