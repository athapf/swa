package de.thaso.fum.ws;

import org.apache.commons.lang3.StringUtils;

public class FumService {

    public String ping(String text) {
        if (text == null) {
            return "Service is up and available";
        }
        return "Service is up and available, message: " + text;
    }

    public FumLoginUser findLoginUser(final String loginName) {
        if(StringUtils.equals(loginName, "herbie")) {
            final FumLoginUser fumLoginUser = new FumLoginUser();
            fumLoginUser.setGivenName("Herbert");
            fumLoginUser.setSureName("Mustermann");
            fumLoginUser.setLoginName(loginName);
            fumLoginUser.setPassword("geheim");
            fumLoginUser.setId(2517389L);
            return fumLoginUser;
        }
        return null;
    }

    public Long storeLoginUser(final FumLoginUser loginUser) {
        if(loginUser != null && StringUtils.isNotEmpty(loginUser.getPassword())) {
            return 78356282L;
        }
        return null;
    }
}
