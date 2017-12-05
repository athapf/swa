package de.thaso.swa.be.business;

import de.thaso.swa.be.business.mapper.ProcessStateMapper;
import de.thaso.swa.be.service.ProcessStateData;
import de.thaso.swa.be.service.ProcessStateService;
import de.thaso.mpt.db.store.NickNameDAO;
import de.thaso.mpt.db.store.NickNameEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * NickNameImpl
 *
 * @author thaler
 * @since 21.09.16
 */
@Stateless
@Remote(ProcessStateService.class)
public class ProcessStateServiceImpl implements ProcessStateService {

    public static final Logger LOG = LoggerFactory.getLogger(ProcessStateServiceImpl.class);

    @Inject
    private NickNameDAO nickNameDAO;

    @Inject
    private ProcessStateMapper processStateMapper;

    @Override
    public void storeNickName(final ProcessStateData processStateData) {
        final NickNameEntity nickNameEntity = processStateMapper.nickNameToEntity(processStateData);
        nickNameDAO.storeNickName(nickNameEntity);
    }

    @Override
    public List<ProcessStateData> findByName(final String name) {
        LOG.debug("findByName( {} ) ...", name);
        final List<NickNameEntity> lastNameList = nickNameDAO.findByName(name);
        final List<ProcessStateData> processStateDataList = processStateMapper.nickNameListToDOList(lastNameList);
        LOG.debug(" ... found {} messages", processStateDataList.size());
        return processStateDataList;
    }

    @Override
    public List<ProcessStateData> findByNickName(final String name, final String nick) {
        LOG.debug("findByNickName( {}, {} )", name, nick);
        final List<NickNameEntity> nameEntityList = nickNameDAO.findByNickName(name, nick);
        return processStateMapper.nickNameListToDOList(nameEntityList);
    }
}
