package de.thaso.swa.be.workshop.business;

import de.thaso.swa.be.workshop.WorkshopData;
import de.thaso.swa.be.workshop.WorkshopService;
import de.thaso.swa.be.workshop.WorkshopServiceLocal;
import de.thaso.swa.db.workshop.WorkshopEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * WorkshopImpl
 *
 * @author thaler
 * @since 2017-12-12
 */
@Stateless
@Remote(WorkshopService.class)
@Local(WorkshopServiceLocal.class)
public class WorkshopServiceImpl implements WorkshopService {

    public static final Logger LOG = LoggerFactory.getLogger(WorkshopServiceImpl.class);

    //@Inject
    //private WorkshopDAO workshopDAO;

    //@Inject
    //private WorkshopMapper workshopMapper;

    @Override
    public void storeWorkshop(final WorkshopData workshopData) {
        //final WorkshopEntity workshopEntity = workshopMapper.workshopToEntity(workshopData);
        //workshopDAO.storeWorkshop(workshopEntity);
    }

    @Override
    public List<WorkshopData> findAllWorkshops() {
        LOG.debug("findAllWorkshops ...");
        final List<WorkshopEntity> workshopEntityList = new ArrayList<>(); //workshopDAO.findAll();
        //return workshopMapper.workshopListToDOList(workshopEntityList);
        return new ArrayList<>();
    }
}
