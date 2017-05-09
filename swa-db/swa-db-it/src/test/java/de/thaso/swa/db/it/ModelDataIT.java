package de.thaso.swa.db.it;

import de.thaso.swa.db.it.base.DbTestBase;
import de.thaso.swa.db.store.process.ModelActionEntity;
import de.thaso.swa.db.store.process.ModelDataDAO;
import de.thaso.swa.db.store.process.ModelStateEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * ModelDataIT
 *
 * @author thaler
 * @since 2017-04-26
 */
public class ModelDataIT extends DbTestBase {

    @InjectMocks
    private ModelDataDAO underTest;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testLoadModelState() {
        updateDatabase("ModelDataIT/testLoadModelState.xml");
        // when
        final ModelStateEntity modelStateEntity = underTest.loadModelStateById(1L);
        // then
        assertThat(modelStateEntity.getId(), is(1L));
        assertThat(modelStateEntity.getState(), is("BEGIN"));
        assertThat(modelStateEntity.getGraph(), is("Test"));
    }

    @Test
    public void testFindModelStateByState() {
        updateDatabase("ModelDataIT/testFindModelStateByState.xml");
        // when
        final ModelStateEntity modelStateEntity = underTest.findModelStateByState("START", "ShoppingCart");
        // then
        assertThat(modelStateEntity.getId(), is(greaterThanOrEqualTo(1000000L)));
        assertThat(modelStateEntity.getState(), is("START"));
        assertThat(modelStateEntity.getGraph(), is("ShoppingCart"));
    }

    @Test
    public void testFindAllPossibleActionsFromState() {
        // when
        final List<ModelActionEntity> modelActionEntityList = underTest.findAllPossibleActionsFromState("CLOSED", "ShoppingCart");
        // then
        assertThat(modelActionEntityList.size(), is(4));
        assertThat(extractActions(modelActionEntityList), containsInAnyOrder("stop", "save", "reopen", "drop"));
    }

    @Test
    public void testFindPossiblePublicActionsFromState() {
        // when
        final List<ModelActionEntity> modelActionEntityList = underTest.findPossiblePublicActionsFromState("CLOSED", "ShoppingCart");
        // then
        assertThat(modelActionEntityList.size(), is(3));
        assertThat(extractActions(modelActionEntityList), containsInAnyOrder("save", "reopen", "drop"));
    }

    private List<String> extractActions(final List<ModelActionEntity> modelActionEntityList) {
        final List<String> result = new ArrayList<>();
        for (final ModelActionEntity modelActionEntity : modelActionEntityList) {
            result.add(modelActionEntity.getAction());
        }
        return result;
    }
}
