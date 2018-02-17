package de.thaso.swa.be.workshop.business;

import de.thaso.swa.be.workshop.WorkshopData;
import de.thaso.swa.be.workshop.WorkshopService;
import de.thaso.swa.be.workshop.WorkshopServiceLocal;
import de.thaso.swa.be.workshop.business.mapper.WorkshopMapper;
import de.thaso.swa.db.workshop.WorkshopDAO;
import de.thaso.swa.db.workshop.WorkshopEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * WorkshopImpl
 *
 * @author thaler
 * @since 2017-12-12
 */
@Stateless(name = "WorkshopService")
@LocalBean
@Remote(WorkshopService.class)
@Local(WorkshopServiceLocal.class)
public class WorkshopServiceImpl implements WorkshopService, WorkshopServiceLocal {

    public static final Logger LOG = LoggerFactory.getLogger(WorkshopServiceImpl.class);

    @Inject
    private WorkshopDAO workshopDAO;

    @Inject
    private WorkshopMapper workshopMapper;

    @Override
    public void storeWorkshop(final WorkshopData workshopData) {
        //final WorkshopEntity workshopEntity = workshopMapper.workshopToEntity(workshopData);
        //workshopDAO.storeWorkshop(workshopEntity);
    }

    @Override
    public List<WorkshopData> findAllWorkshops() {
        LOG.debug("findAllWorkshops ...");

        List<WorkshopEntity> workshopEntityList = workshopDAO.findAll();
        return workshopMapper.workshopListToDOList(workshopEntityList);
    }
}
