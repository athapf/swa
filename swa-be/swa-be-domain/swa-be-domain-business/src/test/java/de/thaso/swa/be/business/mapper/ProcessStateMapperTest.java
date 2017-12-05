package de.thaso.swa.be.business.mapper;

import de.thaso.swa.be.service.ProcessStateData;
import de.thaso.mpt.db.store.NickNameEntity;
import de.thaso.swa.db.store.process.ProcessStateEntity;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * ProcessStateMapperTest
 *
 * @author thaler
 * @since 22.09.16
 */
public class ProcessStateMapperTest {

    @InjectMocks
    private ProcessStateMapper underTest = Mappers.getMapper(ProcessStateMapper.class);

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void convertToEntity_WhenDOIsNull() {
        // when
        final ProcessStateEntity result = underTest.processStateToEntity(null);
        // then
        assertThat(result,is(nullValue()));
    }

    @Test
    public void convertListToDO() {
        // given
        final ArrayList<ProcessStateEntity> processStateEntityList = new ArrayList<>();
        final ProcessStateEntity firstMessageEntity = new ProcessStateEntity();
        firstMessageEntity.setId(743L);
        firstMessageEntity.setDomainobjectName("Hello");
        processStateEntityList.add(firstMessageEntity);
        final ProcessStateEntity secondMessageEntity = new ProcessStateEntity();
        secondMessageEntity.setId(234L);
        secondMessageEntity.setDomainobjectName("World");
        processStateEntityList.add(secondMessageEntity);
        // when
        final List<ProcessStateData> processStateDataList = underTest.processStateListToDOList(processStateEntityList);
        // then
        assertThat(processStateDataList.size(),is(processStateEntityList.size()));

        ProcessStateData firstProcessStateData = processStateDataList.get(0);
        assertThat(firstProcessStateData.getId(),is(firstMessageEntity.getId()));
        assertThat(firstProcessStateData.getName(),is(firstMessageEntity.getDomainobjectName()));
        ProcessStateData secondProcessStateData = processStateDataList.get(1);
        assertThat(secondProcessStateData.getId(),is(secondMessageEntity.getId()));
        assertThat(secondProcessStateData.getName(),is(secondMessageEntity.getDomainobjectName()));
    }
}
