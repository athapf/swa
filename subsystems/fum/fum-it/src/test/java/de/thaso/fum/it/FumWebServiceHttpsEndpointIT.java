package de.thaso.fum.it;

import com.sun.xml.ws.developer.JAXWSProperties;
import de.thaso.fum.utils.PropertiesManager;
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
import javax.xml.ws.Service;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FumWebServiceHttpsEndpointIT {

    public static final String URL_KEY = "fum.webservice.https";

    public static final String WEB_SERVICE_URL_PATH = "fum/services/FumWS";
    public static final String KEYSTORE_FILE = "/keystore.jks";
    public static final String KEYSTORE_TYPE = "jks";
    public static final String KEYSTORE_PASSWORD = "geheim";
    public static final String WSDL_FILE = "/fumWebServiceHttps.wsdl";
    public static final String PING_TEXT = RandomStringUtils.randomAlphanumeric(10);

    private URL url;
    private HttpsURLConnection connection;
    private FumWSPortType servicePort;

    @Before
    public void setUp() throws Exception {
        final Properties properties = PropertiesManager.readDevelopProperties();

//        System.setProperty("javax.net.debug", "all");
        System.setProperty("javax.net.ssl.trustStore", "");
        System.setProperty("javax.net.ssl.keyStore", "");
        final String urlString = StringUtils.join(properties.getProperty(URL_KEY),"/",WEB_SERVICE_URL_PATH);
        url = new URL(StringUtils.join(urlString, "?wsdl"));
        connection = (HttpsURLConnection) url.openConnection();
        connection.setSSLSocketFactory(createSSLSocketFactory());

        url = this.getClass().getResource(WSDL_FILE);
        final Service service = Service.create(url, FumWS.SERVICE);
        servicePort = service.getPort(FumWSPortType.class);

        ((BindingProvider) servicePort).getRequestContext().put(JAXWSProperties.SSL_SOCKET_FACTORY, createSSLSocketFactory());
        ((BindingProvider) servicePort).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, StringUtils.join(urlString, ".FumWSHttpEndpoint/"));
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
