package de.thaso.swa.be.workshop.business.mapper;

import de.thaso.swa.be.workshop.WorkshopData;
import de.thaso.swa.db.workshop.WorkshopEntity;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * WorkshopMapperTest
 *
 * @author thaler
 * @since 2017-12-12
 */
public class WorkshopMapperTest {

    @InjectMocks
    private WorkshopMapper underTest = Mappers.getMapper(WorkshopMapper.class);

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void convertToEntity_WhenDOIsNull() {
        // when
        final WorkshopEntity result = underTest.workshopToEntity(null);
        // then
        assertThat(result,is(nullValue()));
    }

    @Test
    public void convertListToDO() {
        // given
        final ArrayList<WorkshopEntity> workshopEntityList = new ArrayList<>();
        final WorkshopEntity firstMessageEntity = new WorkshopEntity();
        firstMessageEntity.setId(743L);
        firstMessageEntity.setTitle("hallo");
        workshopEntityList.add(firstMessageEntity);
        final WorkshopEntity secondMessageEntity = new WorkshopEntity();
        secondMessageEntity.setId(234L);
        secondMessageEntity.setTitle("Welt");
        workshopEntityList.add(secondMessageEntity);
        // when
        final List<WorkshopData> workshopDataList = underTest.workshopListToDOList(workshopEntityList);
        // then
        assertThat(workshopDataList.size(),is(workshopEntityList.size()));

        WorkshopData firstWorkshopData = workshopDataList.get(0);
        assertThat(firstWorkshopData.getId(),is(firstMessageEntity.getId()));
        WorkshopData secondWorkshopData = workshopDataList.get(1);
        assertThat(secondWorkshopData.getId(),is(secondMessageEntity.getId()));
    }
}
