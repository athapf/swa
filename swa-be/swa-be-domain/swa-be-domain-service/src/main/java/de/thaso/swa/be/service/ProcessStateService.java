package de.thaso.swa.be.service;

import java.util.List;

/**
 * ProcessStateService
 *
 * @author thaler
 * @since 21.09.16
 */
public interface ProcessStateService {

    void storeProcessState(ProcessStateData processStateData);

    List<ProcessStateData> findByName(String name);

    List<ProcessStateData> findByProcessState(String name, String nick);
}
