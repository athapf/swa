package de.thaso.swa.be.business;

import de.thaso.swa.be.business.mapper.ProductMapper;
import de.thaso.swa.be.product.service.ProductData;
import de.thaso.swa.be.product.service.ProductService;
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
@Remote(ProductService.class)
public class ProductServiceImpl implements ProductService {

    public static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Inject
    private NickNameDAO nickNameDAO;

    @Inject
    private ProductMapper productMapper;

    @Override
    public void storeNickName(final ProductData productData) {
        final NickNameEntity nickNameEntity = productMapper.nickNameToEntity(productData);
        nickNameDAO.storeNickName(nickNameEntity);
    }

    @Override
    public List<ProductData> findByName(final String name) {
        LOG.debug("findByName( {} ) ...", name);
        final List<NickNameEntity> lastNameList = nickNameDAO.findByName(name);
        final List<ProductData> productDataList = productMapper.nickNameListToDOList(lastNameList);
        LOG.debug(" ... found {} messages", productDataList.size());
        return productDataList;
    }

    @Override
    public List<ProductData> findByNickName(final String name, final String nick) {
        LOG.debug("findByNickName( {}, {} )", name, nick);
        final List<NickNameEntity> nameEntityList = nickNameDAO.findByNickName(name, nick);
        return productMapper.nickNameListToDOList(nameEntityList);
    }
}
