package de.thaso.fum.it;

import de.thaso.fum.ws.FumWS;
import de.thaso.fum.ws.FumWSPortType;
import de.thaso.fum.ws.xsd.FumLoginUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FumWebServiceIT {

    public static final String PING_TEXT = RandomStringUtils.randomAlphanumeric(10);

    private URL url;
    private HttpURLConnection connection;
    private FumWS service;
    private FumWSPortType servicePort;

    @Before
    public void setUp() throws Exception {
        url = new URL("http://localhost:65401/fum/services/FumWS?wsdl");
        connection = (HttpURLConnection) url.openConnection();
        service = new FumWS(url, FumWS.SERVICE);
        servicePort = service.getFumWSHttpSoap12Endpoint();
    }

    @Test
    public void readWsdlFumWS() throws IOException {
        // given
        connection.setRequestMethod("GET");
        // when
        final int result = connection.getResponseCode();
        // then
        assertThat(result, is(200));
        assertThat(connection.getResponseMessage(), is("OK"));
    }

    @Test
    public void readPing() throws IOException {
        // when
        final String result = servicePort.ping(null);
        // then
        assertThat(result, containsString("Service is up and available"));
    }

    @Test
    public void readPingWithName() throws IOException {
        // when
        final String result = servicePort.ping(PING_TEXT);
        // then
        assertThat(result, containsString(PING_TEXT));
    }

    @Test
    public void findLoginUser_whenLoginNameIsNull() {
        // when
        final FumLoginUser loginUser = servicePort.findLoginUser(null);
        // then
        assertThat(loginUser, is(nullValue()));
    }

    @Test
    public void findLoginUser_whenLoginNameIsEmpty() {
        // when
        final FumLoginUser loginUser = servicePort.findLoginUser(StringUtils.EMPTY);
        // then
        assertThat(loginUser, is(nullValue()));
    }

    @Test
    public void findLoginUser() {
        // when
        final FumLoginUser loginUser = servicePort.findLoginUser("herbie");
        // then
        assertThat(loginUser, is(notNullValue()));
        assertThat(loginUser.getId().getValue(), is(2517389L));
        assertThat(loginUser.getGivenName().getValue(), is("Herbert"));
        assertThat(loginUser.getSureName().getValue(), is("Mustermann"));
        assertThat(loginUser.getLoginName().getValue(), is("herbie"));
        assertThat(loginUser.getPassword().getValue(), is("geheim"));
    }
}
