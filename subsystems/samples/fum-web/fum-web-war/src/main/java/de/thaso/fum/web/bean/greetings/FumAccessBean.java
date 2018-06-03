package de.thaso.fum.web.bean.greetings;

import de.thaso.fum.web.ra.api.FumConnection;
import de.thaso.fum.web.ra.api.FumConnectionFactory;
import de.thaso.fum.web.ra.api.FumWebLoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.resource.ConnectionFactoryDefinition;
import javax.resource.ResourceException;
import javax.resource.spi.TransactionSupport;
import java.io.Serializable;

@Named
@ApplicationScoped
@ConnectionFactoryDefinition(
    name = "java:/FumConnectionFactory",
    interfaceName = "de.thaso.fum.web.ra.api.FumConnectionFactory",
    resourceAdapter = "#fum-web-rar",
    minPoolSize = 5,
    transactionSupport =
            TransactionSupport.TransactionSupportLevel.NoTransaction
)
public class FumAccessBean implements Serializable {
    private static final long serialVersionUID = 8073962951966504851L;
    private static final Logger LOG = LoggerFactory.getLogger(FumAccessBean.class);

    @Resource(lookup = "java:/eis/FumConnectionFactory")
    private FumConnectionFactory connectionFactory;

    private FumConnection connection = null;
    private FumWebLoginUser loginUser;

    public FumAccessBean() {
        loginUser = new FumWebLoginUser();
    }

    public String connect() {
        String page = "index";
        if (connection == null) {
            try {
                LOG.info("[ResourceAccessBean] Getting connection from the RA");
                connection = (FumConnection) connectionFactory.getConnection();
                page = "fum";
            } catch (ResourceException e) {
                LOG.info(e.getMessage());
            }
        }
        return page;

    }

    public String disconnect() {
        try {
            connection.close();
            connection = null;
        } catch (ResourceException e) {
            LOG.info(e.getMessage());
        }
        return "index";
    }

    public FumWebLoginUser findLoginUser() { return connection.findLoginUser("hallo"); }
}