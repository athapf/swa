package de.thaso.swa.be.service;

import java.util.List;

/**
 * WorkshopService
 *
 * @author thaler
 * @since 21.09.16
 */
public interface WorkshopService {

    void storeWorkshop(WorkshopData workshopData);

    List<WorkshopData> findOpenWorkshops();
}
