package de.thaso.swa.be.common.service;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * BaseDataTest
 *
 * @author thaler
 * @since 2017-04-24
 */
public class RecordBaseTest {

    private static final long TEST_ID = 21L;
    private static final long SECOND_ID = 17L;

    private RecordBase underTest;

    private RecordBase secondData;

    @Before
    public void setUp() {
        underTest = new DummyRecordBase();
        secondData = new DummyRecordBase();
    }

    @Test
    public void equals_whenSameObject() {
        assertThat(underTest.equals(underTest),is(true));
    }

    @Test
    public void equals_whenNullObject() {
        assertThat(underTest.equals(null),is(false));
    }

    @Test
    public void equals_whenDifferendObjects() {
        assertThat(underTest.equals("foo"),is(false));
    }

    @Test
    public void equals_whenBothIdNull() {
        underTest.setId(null);
        secondData.setId(null);
        // then
        assertThat(underTest.equals(secondData),is(false));
    }

    @Test
    public void equals_whenBothIdEquals() {
        // given
        underTest.setId(TEST_ID);
        secondData.setId(TEST_ID);
        // then
        assertThat(underTest.equals(secondData),is(true));
    }

    @Test
    public void equals_whenSecondIdNull() {
        // given
        underTest.setId(TEST_ID);
        secondData.setId(null);
        // then
        assertThat(underTest.equals(secondData),is(false));
    }

    @Test
    public void equals_whenSecondIdDiffers() {
        // given
        underTest.setId(TEST_ID);
        secondData.setId(SECOND_ID);
        // then
        assertThat(underTest.equals(secondData),is(false));
    }

    private class DummyRecordBase extends RecordBase { }
}