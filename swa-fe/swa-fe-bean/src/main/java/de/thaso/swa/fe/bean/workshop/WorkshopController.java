package de.thaso.swa.fe.bean.workshop;

import de.thaso.swa.be.workshop.WorkshopData;
import de.thaso.swa.be.workshop.WorkshopService;
import de.thaso.swa.fe.bean.workshop.mapper.WorkshopModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class WorkshopController {

    private static final Logger LOG = LoggerFactory.getLogger(WorkshopController.class);

    @EJB(lookup = "java:global/swa-app/swa-be-workshop-business/WorkshopService!de.thaso.swa.be.workshop.WorkshopService")
    private WorkshopService workshopService;

    @Inject
    private WorkshopTableModel greetingsModel;

    @Inject
    private WorkshopModel workshopModel;

    @Inject
    private WorkshopModelMapper workshopModelMapper;

    public WorkshopModel loadWorkshop(final Long number) {
        final WorkshopModel workshopModel = new WorkshopModel();
        workshopModel.setTitle("Hello, World!");
        workshopModel.setNumber(number.intValue());
        return workshopModel;
    }

    public WorkshopModel loadFirstWorkshop() {
        List<WorkshopData> workshopDataList = workshopService.findAllWorkshops();
        List<WorkshopModel> workshopModelList = workshopModelMapper.workshopListToModelList(workshopDataList);

        return workshopModelList.get(0);
    }
}
