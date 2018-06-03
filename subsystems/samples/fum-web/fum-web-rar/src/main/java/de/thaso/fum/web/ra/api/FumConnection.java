package de.thaso.fum.web.ra.api;

import javax.resource.ResourceException;
import javax.resource.cci.Connection;

public interface FumConnection extends Connection{

    FumWebLoginUser findLoginUser(final String loginName);

    Long storeLoginUser(final FumWebLoginUser loginUser);

    void close() throws ResourceException;
}
