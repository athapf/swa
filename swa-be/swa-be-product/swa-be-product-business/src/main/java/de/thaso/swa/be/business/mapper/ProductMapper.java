package de.thaso.swa.be.business.mapper;

import de.thaso.swa.be.product.service.ProductData;
import de.thaso.mpt.db.store.NickNameEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * ProductMapper
 *
 * @author thaler
 * @since 22.09.16
 */
@Mapper
public interface ProductMapper {

    ProductData nickNameToDO(NickNameEntity nickNameEntity);

    NickNameEntity nickNameToEntity(ProductData productData);

    List<ProductData> nickNameListToDOList(List<NickNameEntity> nickNameEntityList);
}
