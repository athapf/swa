package de.thaso.swa.fe.bean.workshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

public class WorkshopProducer {

    private static final Logger LOG = LoggerFactory.getLogger(WorkshopProducer.class);

    //@EJB(lookup = "java:global/swa-app/swa-be-workshop-business/WorkshopServiceImpl!de.thaso.swa.be.workshop.WorkshopService")
    //private WorkshopService workshopService;

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

        WorkshopModel workshop = new WorkshopModel();
        workshop.setTitle("Java in JEE");
        workshopTableModel.addWorkshop(workshop);

        return workshopTableModel;
    }
}
