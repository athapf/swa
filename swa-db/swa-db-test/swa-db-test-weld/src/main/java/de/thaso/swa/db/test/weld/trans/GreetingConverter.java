package de.thaso.swa.db.test.weld.trans;

/**
 * GreetingConverter
 *
 * @author thaler
 * @since 2017-05-18
 */
public class GreetingConverter extends Converter<GreetingData, GreetingExtern> {

    @Override
    public GreetingData mapTo(final GreetingData dest, final GreetingExtern source) {
        dest.setGreeting(source.getGreeting());
        dest.setName(source.getName());
        return dest;
    }
}
