package de.thaso.swa.db.test.weld.trans;

/**
 * Converter
 *
 * @author thaler
 * @since 2017-05-18
 */
public abstract class Converter<DATA, EXTERN> {

    abstract public DATA mapTo(DATA dest, EXTERN source);
}
