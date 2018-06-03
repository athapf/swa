package de.thaso.fum.web.ra.api;

import javax.resource.Referenceable;
import javax.resource.ResourceException;
import java.io.Serializable;

public interface FumConnectionFactory extends Serializable, Referenceable {

    FumConnection getConnection() throws ResourceException;
}
