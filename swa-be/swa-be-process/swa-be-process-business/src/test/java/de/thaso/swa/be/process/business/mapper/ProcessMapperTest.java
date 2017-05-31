package de.thaso.swa.be.process.business.mapper;

import de.thaso.swa.be.process.service.ProcessData;
import de.thaso.mpt.db.store.NickNameEntity;
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
 * NickNameMapperTest
 *
 * @author thaler
 * @since 22.09.16
 */
public class ProcessMapperTest {

    @InjectMocks
    private ProcessMapper underTest = Mappers.getMapper(ProcessMapper.class);

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void convertToEntity_WhenDOIsNull() {
//        // when
//        final NickNameEntity result = underTest.dataToEntity(null);
//        // then
//        assertThat(result,is(nullValue()));
    }

    @Test
    public void convertListToDO() {
//        // given
//        final ArrayList<NickNameEntity> nickNameEntityList = new ArrayList<>();
//        final NickNameEntity firstMessageEntity = new NickNameEntity();
//        firstMessageEntity.setId(743L);
//        firstMessageEntity.setName("Hello");
//        nickNameEntityList.add(firstMessageEntity);
//        final NickNameEntity secondMessageEntity = new NickNameEntity();
//        secondMessageEntity.setId(234L);
//        secondMessageEntity.setName("World");
//        nickNameEntityList.add(secondMessageEntity);
//        // when
//        final List<ProcessData> processDataList = underTest.entityListToDataList(nickNameEntityList);
//        // then
//        assertThat(processDataList.size(),is(nickNameEntityList.size()));
//
//        ProcessData firstProcessData = processDataList.get(0);
//        assertThat(firstProcessData.getId(),is(firstMessageEntity.getId()));
//        assertThat(firstProcessData.getName(),is(firstMessageEntity.getName()));
//        ProcessData secondProcessData = processDataList.get(1);
//        assertThat(secondProcessData.getId(),is(secondMessageEntity.getId()));
//        assertThat(secondProcessData.getName(),is(secondMessageEntity.getName()));
    }
}
