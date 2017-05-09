package de.thaso.swa.db.it.base;

import de.thaso.swa.db.it.utils.PersistenceStoreHelper;
import de.thaso.swa.db.schema.DatabaseManager;
import de.thaso.swa.db.schema.PropertiesManager;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.db2.Db2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Spy;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DbTestBase
 *
 * @author thaler
 * @since 13.09.16
 */
public class DbTestBase {

    @Spy
    protected EntityManager entityManager = PersistenceStoreHelper.getEntityManager();

    private java.sql.Connection connection;
    private IDatabaseConnection databaseConnection;

    @BeforeClass
    public static void prepareDatabase() {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.createDatabase(PropertiesManager.readDevelopProperties());
    }

    @Before
    public void setUpConnection() {
        entityManager.getTransaction().begin();
        connection = entityManager.unwrap(java.sql.Connection.class);

        initializeDatabase();
    }

    @After
    public void tearDownConnection() {
        entityManager.getTransaction().commit();
    }

    public Connection getConnection() {
        return connection;
    }

    private void initializeDatabase() {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        try {
            databaseConnection = new DatabaseConnection(connection);

            final DatabaseConfig config = databaseConnection.getConfig();
            final Db2DataTypeFactory dataTypeFactory = new Db2DataTypeFactory();
            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, dataTypeFactory);

            IDataSet dataSet = builder.build(this.getClass().getResourceAsStream("/dbunit/base-setup.xml"));
            DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataSet);
        } catch (DataSetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DatabaseUnitException e) {
            e.printStackTrace();
        }
    }

    protected void updateDatabase(final String setupFile) {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        try {
            IDataSet dataSet = builder.build(this.getClass().getResourceAsStream("/dbunit/" + setupFile));
            DatabaseOperation.REFRESH.execute(databaseConnection, dataSet);
        } catch (DataSetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DatabaseUnitException e) {
            e.printStackTrace();
        }
    }
}
