package de.thaso.fum.web.ra.api;

public interface FumConnection {

    public FumWebLoginUser findLoginUser(final String loginName);
    public Long storeLoginUser(final FumWebLoginUser loginUser);
    public void close();
}
