package de.thaso.swa.be.business;

import de.thaso.swa.be.business.mapper.OrderMapper;
import de.thaso.swa.be.service.NickNameData;
import de.thaso.swa.be.service.NickNameService;
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
@Remote(NickNameService.class)
public class OrderServiceImpl implements NickNameService {

    public static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Inject
    private NickNameDAO nickNameDAO;

    @Inject
    private OrderMapper orderMapper;

    @Override
    public void storeNickName(final NickNameData nickNameData) {
        final NickNameEntity nickNameEntity = orderMapper.nickNameToEntity(nickNameData);
        nickNameDAO.storeNickName(nickNameEntity);
    }

    @Override
    public List<NickNameData> findByName(final String name) {
        LOG.debug("findByName( {} ) ...", name);
        final List<NickNameEntity> lastNameList = nickNameDAO.findByName(name);
        final List<NickNameData> nickNameDataList = orderMapper.nickNameListToDOList(lastNameList);
        LOG.debug(" ... found {} messages", nickNameDataList.size());
        return nickNameDataList;
    }

    @Override
    public List<NickNameData> findByNickName(final String name, final String nick) {
        LOG.debug("findByNickName( {}, {} )", name, nick);
        final List<NickNameEntity> nameEntityList = nickNameDAO.findByNickName(name, nick);
        return orderMapper.nickNameListToDOList(nameEntityList);
    }
}
