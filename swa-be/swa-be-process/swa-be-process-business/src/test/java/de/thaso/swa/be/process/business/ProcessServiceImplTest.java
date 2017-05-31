package de.thaso.swa.be.process.business;

import de.thaso.swa.be.process.business.mapper.ProcessMapper;
import de.thaso.swa.be.process.service.ProcessData;
import de.thaso.mpt.db.store.NickNameDAO;
import de.thaso.mpt.db.store.NickNameEntity;
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
 * ProcessServiceImplTest
 *
 * @author thaler
 * @since 22.09.16
 */
public class ProcessServiceImplTest {

    @InjectMocks
    private ProcessServiceImpl underTest;

    @Mock
    private NickNameDAO nickNameDAO;

    @Mock
    private ProcessMapper processMapper;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testName() {
//        // given
//        final ProcessData processData = new ProcessData();
//        final NickNameEntity nickNameEntity = new NickNameEntity();
//        when(processMapper.dataToEntity(processData)).thenReturn(nickNameEntity);
//        // when
//        underTest.storeNickName(processData);
//        // then
//        verify(nickNameDAO).storeNickName(nickNameEntity);
    }

    @Test
    public void testFindByName() {
//        // given
//        final String name = "Hugo";
//        final List<NickNameEntity> nickNameEntityList = new ArrayList<>();
//        when(nickNameDAO.findByName(name)).thenReturn(nickNameEntityList);
//        final List<ProcessData> processDataList = new ArrayList<>();
//        when(processMapper.entityListToDataList(nickNameEntityList)).thenReturn(processDataList);
//        // when
//        final List<ProcessData> result = underTest.findByName(name);
//        // then
//        assertThat(result, is(processDataList));
    }
}
