package de.thaso.fum.web.ra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.resource.ResourceException;
import javax.resource.spi.ActivationSpec;
import javax.resource.spi.BootstrapContext;
import javax.resource.spi.Connector;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.transaction.xa.XAResource;

@Connector(
        displayName = "FumResourceAdapter",
        vendorName = "SWA FUM",
        version = "1.0"
)
public class FumResourceAdapter implements ResourceAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(FumResourceAdapter.class);

//    @ConfigProperty(defaultValue = "FumResourceAdapter", supportsDynamicUpdates = true)
//    private String name;
//
//    public FumResourceAdapter() {
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    @Override
    public void start(BootstrapContext bootstrapContext) throws ResourceAdapterInternalException {
        LOG.debug("start");
    }

    @Override
    public void stop() {
        LOG.debug("stop");
    }

    @Override
    public void endpointActivation(MessageEndpointFactory messageEndpointFactory, ActivationSpec activationSpec) throws ResourceException {
        LOG.debug("endpointActivation");
    }

    @Override
    public void endpointDeactivation(MessageEndpointFactory messageEndpointFactory, ActivationSpec activationSpec) {
        LOG.debug("endpointDeactivation");
    }

    @Override
    public XAResource[] getXAResources(ActivationSpec[] activationSpecs) throws ResourceException {
        LOG.debug("getXAResources");
        return null;
//        return new XAResource[0];
    }
}
