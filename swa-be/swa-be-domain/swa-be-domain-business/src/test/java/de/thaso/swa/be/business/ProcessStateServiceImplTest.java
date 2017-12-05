package de.thaso.swa.be.business;

import de.thaso.swa.be.business.mapper.ProcessStateMapper;
import de.thaso.swa.be.service.ProcessStateData;
import de.thaso.swa.db.store.process.ProcessModelDAO;
import de.thaso.swa.db.store.process.ProcessStateEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * ProcessStateServiceImplTest
 *
 * @author thaler
 * @since 22.09.16
 */
public class ProcessStateServiceImplTest {

    @InjectMocks
    private ProcessStateServiceImpl underTest;

    @Mock
    private ProcessModelDAO nickNameDAO;

    @Mock
    private ProcessStateMapper processStateMapper;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testName() {
        // given
        final ProcessStateData processStateData = new ProcessStateData();
        final ProcessStateEntity processStateEntity = new ProcessStateEntity();
        when(processStateMapper.processStateToEntity(processStateData)).thenReturn(processStateEntity);
        // when
        underTest.storeNickName(processStateData);
        // then
        verify(nickNameDAO).storeProcess(processStateEntity);
    }

    @Test
    public void testFindByName() {
        // given
        final String name = "Hugo";
        final List<ProcessStateEntity> processStateEntityList = new ArrayList<>();
        when(nickNameDAO.findByDomainObject(name, null)).thenReturn(processStateEntityList);
        final List<ProcessStateData> processStateDataList = new ArrayList<>();
        when(processStateMapper.processStateListToDOList(processStateEntityList)).thenReturn(processStateDataList);
        // when
        final List<ProcessStateData> result = underTest.findByName(name);
        // then
        assertThat(result, is(processStateDataList));
    }
}
