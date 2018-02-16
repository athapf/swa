package de.thaso.swa.be.workshop.business.mapper;

import de.thaso.swa.be.workshop.WorkshopData;
import de.thaso.swa.db.workshop.WorkshopEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * WorkshopMapper
 *
 * @author thaler
 * @since 2017-12-12
 */
@Mapper
public interface WorkshopMapper {

    WorkshopData workshopToDO(WorkshopEntity workshopEntity);

    WorkshopEntity workshopToEntity(WorkshopData workshopData);

    List<WorkshopData> workshopListToDOList(List<WorkshopEntity> nickNameEntityList);
}
