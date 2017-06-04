package de.thaso.swa.be.business.mapper;

import de.thaso.swa.be.product.service.ProductData;
import de.thaso.swa.db.store.NickNameEntity;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * ProductMapperTest
 *
 * @author thaler
 * @since 22.09.16
 */
public class ProductMapperTest {

    @InjectMocks
    private ProductMapper underTest = Mappers.getMapper(ProductMapper.class);

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void convertToEntity_WhenDOIsNull() {
        // when
        final ProductEntity result = underTest.productToEntity(null);
        // then
        assertThat(result,is(nullValue()));
    }

    @Test
    public void convertListToDO() {
        // given
        final ArrayList<ProductEntity> productEntityList = new ArrayList<>();
        final ProductEntity firstMessageEntity = new ProductEntity();
        firstMessageEntity.setId(743L);
        firstMessageEntity.setName("Hello");
        productEntityList.add(firstMessageEntity);
        final ProductEntity secondMessageEntity = new ProductEntity();
        secondMessageEntity.setId(234L);
        secondMessageEntity.setName("World");
        productEntityList.add(secondMessageEntity);
        // when
        final List<ProductData> productDataList = underTest.productListToDOList(productEntityList);
        // then
        assertThat(productDataList.size(),is(productEntityList.size()));

        ProductData firstProductData = productDataList.get(0);
        assertThat(firstProductData.getId(),is(firstMessageEntity.getId()));
        assertThat(firstProductData.getName(),is(firstMessageEntity.getName()));
        ProductData secondProductData = productDataList.get(1);
        assertThat(secondProductData.getId(),is(secondMessageEntity.getId()));
        assertThat(secondProductData.getName(),is(secondMessageEntity.getName()));
    }
}
