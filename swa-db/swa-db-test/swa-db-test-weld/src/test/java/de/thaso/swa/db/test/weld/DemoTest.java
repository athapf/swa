package de.thaso.swa.db.test.weld;

import de.thaso.swa.db.test.weld.trans.GreetingExtern;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * DemoTest
 *
 * @author thaler
 * @since 2017-05-17
 */
public class DemoTest{

    private static final String HELLO_MEN = "Hello, Men!";

    private Weld weld;
    private WeldContainer container;

    @Before
    public void setUp() {
        weld = new Weld();
        container = weld.initialize();
    }

    @After
    public void tearDown() {
        weld.shutdown();
    }

    @Test
    public void testName() {
        final DemoBean underTest = getBean(DemoBean.class);
        final GreetingExtern extern = new GreetingExtern();
        extern.setGreeting(HELLO_MEN);

        final String result = underTest.greeting(extern);

        assertThat(result, is(notNullValue()));
        assertThat(result, is(HELLO_MEN));
    }

    private <T> T getBean(Class<T> type) {
        return container.instance().select(type).get();
    }
}
