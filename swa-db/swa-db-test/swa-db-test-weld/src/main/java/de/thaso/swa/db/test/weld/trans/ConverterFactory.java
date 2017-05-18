package de.thaso.swa.db.test.weld.trans;

/**
 * ConverterBase
 *
 * @author thaler
 * @since 2017-05-18
 */
public abstract class ConverterFactory<DATA, EXTERN> {

    abstract public Converter<DATA, EXTERN> getConverter();
}
