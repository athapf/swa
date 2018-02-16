package de.thaso.swa.fe.bean.workshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class WorkshopController {

    private static final Logger LOG = LoggerFactory.getLogger(WorkshopController.class);

    @Inject
    private WorkshopTableModel greetingsModel;

    @Inject
    private WorkshopModel workshopModel;

    public WorkshopModel loadWorkshop(final Long number) {
        final WorkshopModel workshopModel = new WorkshopModel();
        workshopModel.setTitle("Hello, World!");
        return workshopModel;
    }
}
