package de.thaso.swa.db.schema;

import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

/**
 * DatabaseManagerIT
 *
 * @author thaler
 * @since 13.09.16
 */
public class DatabaseManagerIT {

    private DatabaseManager underTest;

    private Properties properties;

    @Before
    public void setUp() {
        underTest = new DatabaseManager();

        properties = PropertiesManager.readDevelopProperties();
    }

    @Test
    public void testCreateDatabase() {
        // when
        underTest.createDatabase(properties);
    }
}
