package de.thaso.swa.be.process.business;

import de.thaso.swa.be.common.service.ContextData;
import de.thaso.swa.be.common.service.RecordBase;
import de.thaso.swa.be.common.service.RecordObject;
import de.thaso.swa.be.process.business.model.ProcessModel;
import de.thaso.swa.be.process.service.Action;
import de.thaso.swa.be.process.service.ProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * ProcessServiceImpl
 *
 * @author thaler
 * @since 21.09.16
 */
@Stateless
@Remote(ProcessService.class)
public class ProcessServiceImpl implements ProcessService {

    public static final Logger LOG = LoggerFactory.getLogger(ProcessServiceImpl.class);

    @Inject
    private ProcessModel processModel;

    @Override
    public boolean createProcess(final ContextData contextData, final Action startAction, final RecordObject<RecordBase> domainDataId, final RecordObject<RecordBase> parentDataId) {
        
        return false;
    }
}
