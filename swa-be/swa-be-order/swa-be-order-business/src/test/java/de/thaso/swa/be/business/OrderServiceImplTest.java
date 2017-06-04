package de.thaso.swa.be.business;

import de.thaso.swa.be.business.mapper.OrderMapper;
import de.thaso.swa.be.service.NickNameData;
import de.thaso.swa.db.store.NickNameDAO;
import de.thaso.swa.db.store.NickNameEntity;
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
 * OrderServiceImplTest
 *
 * @author thaler
 * @since 22.09.16
 */
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl underTest;

    @Mock
    private NickNameDAO nickNameDAO;

    @Mock
    private OrderMapper orderMapper;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testName() {
        // given
        final NickNameData nickNameData = new NickNameData();
        final NickNameEntity nickNameEntity = new NickNameEntity();
        when(orderMapper.nickNameToEntity(nickNameData)).thenReturn(nickNameEntity);
        // when
        underTest.storeNickName(nickNameData);
        // then
        verify(nickNameDAO).storeNickName(nickNameEntity);
    }

    @Test
    public void testFindByName() {
        // given
        final String name = "Hugo";
        final List<NickNameEntity> nickNameEntityList = new ArrayList<>();
        when(nickNameDAO.findByName(name)).thenReturn(nickNameEntityList);
        final List<NickNameData> nickNameDataList = new ArrayList<>();
        when(orderMapper.nickNameListToDOList(nickNameEntityList)).thenReturn(nickNameDataList);
        // when
        final List<NickNameData> result = underTest.findByName(name);
        // then
        assertThat(result, is(nickNameDataList));
    }
}
