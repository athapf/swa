package de.thaso.fum.web.ra.api;

import javax.resource.ResourceException;

public interface FumConnectionFactory { //extends Serializable, Referenceable {

    FumConnection getConnection() throws ResourceException;
}
