package de.thaso.swa.be.process.service;

import de.thaso.swa.be.common.service.ContextData;
import de.thaso.swa.be.common.service.RecordBase;
import de.thaso.swa.be.common.service.RecordObject;
import de.thaso.swa.be.process.model.Action;

/**
 * NickNameService
 *
 * @author thaler
 * @since 21.09.16
 */
public interface ProcessService {

    boolean createProcess(ContextData contextData, Action startAction, RecordObject<RecordBase> domainDataId, RecordObject<RecordBase> parentDataId);
}
