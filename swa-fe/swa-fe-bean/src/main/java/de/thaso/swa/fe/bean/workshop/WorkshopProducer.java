package de.thaso.swa.fe.bean.workshop;

import de.thaso.swa.be.workshop.WorkshopData;
import de.thaso.swa.be.workshop.WorkshopService;
import de.thaso.swa.fe.bean.workshop.mapper.WorkshopModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

public class WorkshopProducer {

    private static final Logger LOG = LoggerFactory.getLogger(WorkshopProducer.class);

    @EJB(lookup = "java:global/swa-app/swa-be-workshop-business/WorkshopService!de.thaso.swa.be.workshop.WorkshopService")
    private WorkshopService workshopService;

    @Inject
    private WorkshopModelMapper workshopModelMapper;

    @Produces
    @RequestScoped
    @Named("workshopModel")
    public WorkshopModel produceWorkshopModel(@New WorkshopModel workshopModel) {
        LOG.info("produce workshop model ...");

        workshopModel.setTitle("Java in JEE (42)");

        return workshopModel;
    }

    @Produces
    @RequestScoped
    @Named("workshopTableModel")
    public WorkshopTableModel produceWorkshopTableModel(@New WorkshopTableModel workshopTableModel) {
        LOG.info("produce workshop table model ...");

        List<WorkshopData> workshopDataList = workshopService.findAllWorkshops();
        workshopTableModel.setWorkshopModelList(workshopModelMapper.workshopListToModelList(workshopDataList));
        return workshopTableModel;
    }
}
