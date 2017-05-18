package de.thaso.swa.db.test.weld.trans;

/**
 * GreetingConverterFactory
 *
 * @author thaler
 * @since 2017-05-18
 */
public class GreetingConverterFactory extends ConverterFactory<GreetingData, GreetingExtern> {

    @Override
    public Converter<GreetingData, GreetingExtern> getConverter() {
        return new GreetingConverter();
    }
}
