package de.thaso.swa.be.workshop.business;

import de.thaso.swa.be.workshop.WorkshopData;
import de.thaso.swa.be.workshop.business.mapper.WorkshopMapper;
import de.thaso.swa.db.workshop.WorkshopDAO;
import de.thaso.swa.db.workshop.WorkshopEntity;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

/**
 * WorkshopServiceImplTest
 *
 * @author thaler
 * @since 2017-12-12
 */
@Ignore
public class WorkshopServiceImplTest {

    @InjectMocks
    private WorkshopServiceImpl underTest;

    @Mock
    private WorkshopDAO workshopDAO;

    @Mock
    private WorkshopMapper workshopMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testName() {
        // given
        final WorkshopData workshopData = new WorkshopData();
        final WorkshopEntity workshopEntity = new WorkshopEntity();
        Mockito.when(workshopMapper.workshopToEntity(workshopData)).thenReturn(workshopEntity);
        // when
        underTest.storeWorkshop(workshopData);
        // then
        Mockito.verify(workshopDAO).storeWorkshop(workshopEntity);
    }

    @Test
    public void testFindOpenWorkshops() {
        // given
        final List<WorkshopEntity> workshopEntityList = new ArrayList<>();
        Mockito.when(workshopDAO.findAll()).thenReturn(workshopEntityList);
        final List<WorkshopData> workshopDataList = new ArrayList<>();
        Mockito.when(workshopMapper.workshopListToDOList(workshopEntityList)).thenReturn(workshopDataList);
        // when
        final List<WorkshopData> result = underTest.findAllWorkshops();
        // then
        Assert.assertThat(result, CoreMatchers.is(workshopDataList));
    }
}
