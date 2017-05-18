package de.thaso.swa.db.test.weld;

import de.thaso.swa.db.test.weld.trans.Converter;
import de.thaso.swa.db.test.weld.trans.ConverterFactory;
import de.thaso.swa.db.test.weld.trans.GreetingData;
import de.thaso.swa.db.test.weld.trans.GreetingExtern;

import javax.inject.Inject;

/**
 * DemoBean
 *
 * @author thaler
 * @since 2017-05-18
 */
public class DemoBean {

    @Inject
    private ConverterFactory<GreetingData, GreetingExtern> factory;

    public String greeting(final GreetingExtern extern) {
        final Converter<GreetingData, GreetingExtern> converter = factory.getConverter();
        final GreetingData greetingData = new GreetingData();
        converter.mapTo(greetingData, extern);
        return greetingData.getGreeting();
    }
}
