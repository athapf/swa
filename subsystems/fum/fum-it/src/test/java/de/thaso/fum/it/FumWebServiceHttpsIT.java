package de.thaso.fum.it;

import de.thaso.fum.ws.FumWS;
import de.thaso.fum.ws.FumWSPortType;
import de.thaso.fum.ws.xsd.FumLoginUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.ws.BindingProvider;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FumWebServiceHttpsIT {

    public static final String WEB_SERVICE_URL = "https://server:65401/fum/services/FumWS";
    public static final String KEYSTORE_FILE = "/keystore.jks";
    public static final String KEYSTORE_TYPE = "jks";
    public static final String KEYSTORE_PASSWORD = "geheim";
    public static final String WSDL_FILE = "/funWebServiceHttps.swdl";
    public static final String PING_TEXT = RandomStringUtils.randomAlphanumeric(10);

    private URL url;
    private HttpsURLConnection connection;
    private FumWS service;
    private FumWSPortType servicePort;

    @Before
    public void setUp() throws Exception {
        //System.setProperty("javax.net.debug", "all");
        url = new URL(WEB_SERVICE_URL +"?wsdl");
        connection = (HttpsURLConnection) url.openConnection();
        connection.setSSLSocketFactory(createSSLSocketFactory());

        url = this.getClass().getResource(WSDL_FILE);
        service = new FumWS(url, FumWS.SERVICE);
        servicePort = service.getFumWSHttpSoap12Endpoint();

        ((BindingProvider) servicePort).getRequestContext().put("com.sun.xml.ws.transport.https.client.SSLSocketFactory", createSSLSocketFactory());
        ((BindingProvider) servicePort).getRequestContext().put("javax.xml.ws.service.endpoint.address", WEB_SERVICE_URL + ".FumWSHttpSoap12Endpoint/");
    }

    private SSLSocketFactory createSSLSocketFactory() throws Exception {
        final TrustManagerFactory trustManagerFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(loadKeystore());
        final SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
        return sslContext.getSocketFactory();
    }

    private KeyStore loadKeystore() throws Exception {
        final KeyStore keyStore = KeyStore.getInstance(KEYSTORE_TYPE);
        final InputStream inputStream = this.getClass().getResourceAsStream(KEYSTORE_FILE);
        keyStore.load(inputStream, KEYSTORE_PASSWORD.toCharArray());
        inputStream.close();
        return keyStore;
    }

    @Test
    public void readWsdlFumWS() throws IOException {
        // given
        connection.setRequestMethod("GET");
        // when
        final int result = connection.getResponseCode();
        // then
        assertThat(result, is(200));

/*
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = bufferedReader.readLine();
        while(line != null) {
            System.out.println(line);
            line = bufferedReader.readLine();
        }
*/
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
