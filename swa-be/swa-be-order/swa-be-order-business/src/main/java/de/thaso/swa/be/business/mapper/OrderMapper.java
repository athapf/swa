package de.thaso.swa.be.business.mapper;

import de.thaso.swa.be.service.NickNameData;
import de.thaso.mpt.db.store.NickNameEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * OrderMapper
 *
 * @author thaler
 * @since 22.09.16
 */
@Mapper
public interface OrderMapper {

    NickNameData nickNameToDO(NickNameEntity nickNameEntity);

    NickNameEntity nickNameToEntity(NickNameData nickNameData);

    List<NickNameData> nickNameListToDOList(List<NickNameEntity> nickNameEntityList);
}
