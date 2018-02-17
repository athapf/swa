package de.thaso.swa.fe.bean.workshop.mapper;

import de.thaso.swa.be.workshop.WorkshopData;
import de.thaso.swa.fe.bean.workshop.WorkshopModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface WorkshopModelMapper {

    WorkshopModel workshopToModel(WorkshopData workshopData);

    WorkshopData workshopToData(WorkshopModel workshopModel);

    List<WorkshopModel> workshopListToModelList(List<WorkshopData> workshopDataList);
}
