package de.thaso.swa.be.business.mapper;

import de.thaso.swa.be.service.WorkshopData;
import de.thaso.swa.db.workshop.cart.WorkshopEntity;
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
