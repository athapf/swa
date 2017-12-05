package de.thaso.swa.be.business.mapper;

import de.thaso.swa.be.service.ProcessStateData;
import de.thaso.swa.db.store.process.ProcessStateEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * ProcessStateMapper
 *
 * @author thaler
 * @since 22.09.16
 */
@Mapper
public interface ProcessStateMapper {

    ProcessStateEntity processStateToDO(ProcessStateEntity processStateEntity);

    ProcessStateEntity processStateToEntity(ProcessStateData processStateData);

    List<ProcessStateData> processStateListToDOList(List<ProcessStateEntity> nickNameEntityList);
}
