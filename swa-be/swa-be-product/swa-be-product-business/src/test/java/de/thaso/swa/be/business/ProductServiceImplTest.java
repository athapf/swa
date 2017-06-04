package de.thaso.swa.be.business;

import de.thaso.swa.be.business.mapper.ProductMapper;
import de.thaso.swa.be.product.service.ProductData;
import de.thaso.mpt.db.store.NickNameDAO;
import de.thaso.mpt.db.store.NickNameEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * ProductServiceImplTest
 *
 * @author thaler
 * @since 22.09.16
 */
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl underTest;

    @Mock
    private ProductDAO productDAO;

    @Mock
    private ProductMapper productMapper;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testName() {
        // given
        final ProductData productData = new ProductData();
        final ProductEntity productEntity = new ProductEntity();
        when(productMapper.productToEntity(productData)).thenReturn(productEntity);
        // when
        underTest.storeProduct(productData);
        // then
        verify(productDAO).storeProduct(productEntity);
    }

    @Test
    public void testFindByName() {
        // given
        final String name = "Hugo";
        final List<ProductEntity> productEntityList = new ArrayList<>();
        when(productDAO.findByName(name)).thenReturn(productEntityList);
        final List<ProductData> productDataList = new ArrayList<>();
        when(productMapper.productListToDOList(productEntityList)).thenReturn(productDataList);
        // when
        final List<ProductData> result = underTest.findByName(name);
        // then
        assertThat(result, is(productDataList));
    }
}
