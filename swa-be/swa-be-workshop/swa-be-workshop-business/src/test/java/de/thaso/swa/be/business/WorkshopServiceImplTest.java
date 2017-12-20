package de.thaso.swa.be.business;

import de.thaso.swa.be.business.mapper.WorkshopMapper;
import de.thaso.swa.be.service.WorkshopData;
import de.thaso.swa.db.workshop.cart.WorkshopDAO;
import de.thaso.swa.db.workshop.cart.WorkshopEntity;
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
 * WorkshopServiceImplTest
 *
 * @author thaler
 * @since 2017-12-12
 */
public class WorkshopServiceImplTest {

    @InjectMocks
    private WorkshopServiceImpl underTest;

    @Mock
    private WorkshopDAO workshopDAO;

    @Mock
    private WorkshopMapper workshopMapper;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testName() {
        // given
        final WorkshopData workshopData = new WorkshopData();
        final WorkshopEntity workshopEntity = new WorkshopEntity();
        when(workshopMapper.workshopToEntity(workshopData)).thenReturn(workshopEntity);
        // when
        underTest.storeWorkshop(workshopData);
        // then
        verify(workshopDAO).storeWorkshop(workshopEntity);
    }

    @Test
    public void testFindOpenWorkshops() {
        // given
        final List<WorkshopEntity> workshopEntityList = new ArrayList<>();
        when(workshopDAO.findByTitle("T%")).thenReturn(workshopEntityList);
        final List<WorkshopData> workshopDataList = new ArrayList<>();
        when(workshopMapper.workshopListToDOList(workshopEntityList)).thenReturn(workshopDataList);
        // when
        final List<WorkshopData> result = underTest.findOpenWorkshops();
        // then
        assertThat(result, is(workshopDataList));
    }
}
