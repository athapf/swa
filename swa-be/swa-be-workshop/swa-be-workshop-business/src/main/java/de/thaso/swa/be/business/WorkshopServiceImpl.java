package de.thaso.swa.be.business;

import de.thaso.swa.be.business.mapper.WorkshopMapper;
import de.thaso.swa.be.service.WorkshopData;
import de.thaso.swa.be.service.WorkshopService;
import de.thaso.swa.db.workshop.cart.WorkshopDAO;
import de.thaso.swa.db.workshop.cart.WorkshopEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
@Stateless
@Remote(WorkshopService.class)
public class WorkshopServiceImpl implements WorkshopService {

    public static final Logger LOG = LoggerFactory.getLogger(WorkshopServiceImpl.class);

    @Inject
    private WorkshopDAO workshopDAO;

    @Inject
    private WorkshopMapper workshopMapper;

    @Override
    public void storeWorkshop(final WorkshopData workshopData) {
        final WorkshopEntity workshopEntity = workshopMapper.workshopToEntity(workshopData);
        workshopDAO.storeWorkshop(workshopEntity);
    }

    @Override
    public List<WorkshopData> findOpenWorkshops() {
        LOG.debug("findOpenWorkshops ...");
        final List<WorkshopEntity> workshopEntityList = workshopDAO.findByTitle("T%");
        return workshopMapper.workshopListToDOList(workshopEntityList);
    }
}
