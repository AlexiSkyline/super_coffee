package com.super_coffee.app.service.impl;

import com.super_coffee.app.models.domain.Product;
import com.super_coffee.app.repository.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class ProductServiceTest
{
    @Mock
    private IProductRepository productRepository;
    @InjectMocks
    private ProductService productService;
    private Product product;
    private List<Product> productList;

    @BeforeEach
    void setUp()
    {
        this.product = new Product();
        this.product.set_id( "PRODUCT_01" );
        this.product.setName( "COFFEE" );
        this.product.setDescription( "regular cup of coffee" );
        this.productList = Collections.singletonList( this.product );
    }

    @Test
    void save()
    {
        when( this.productRepository.save( this.product ) ).thenReturn( this.product );
        Product newProduct = this.productService.save( this.product );

        assertThat( newProduct ).isNotNull();
        assertThat( newProduct ).isEqualTo( this.product );
        assertThat( newProduct.getName() ).isEqualTo( this.product.getName() );
    }

    @Test
    void findAll()
    {
        when( this.productRepository.findAllByStatusTrue() ).thenReturn( this.productList );
        List<Product> allProducts =  this.productService.findAll();

        assertThat( allProducts ).isNotNull();
        assertThat( allProducts.size() ).isEqualTo( 1 );
        assertThat( allProducts.get(0).getName() ).isEqualTo( this.product.getName() );
    }

    @Test
    void findById()
    {
        when( this.productRepository.findById( anyString() ) ).thenReturn( Optional.ofNullable( this.product ) );
        Product productFound = this.productService.findById( "PRODUCT_01" );

        assertThat( productFound ).isNotNull();
        assertThat( productFound.getName() ).isEqualTo( this.product.getName() );
        assertThat( productFound ).isEqualTo( this.product );
    }

    @Test
    void countAllDocuments()
    {
        when( this.productRepository.countAllByStatusIsTrue() ).thenReturn( 5 );
        int totalProducts = this.productService.countAllDocuments();

        assertThat( totalProducts ).isNotZero();
        assertThat( totalProducts == 5 ).isTrue();
        assertThat( totalProducts ).isPositive();
    }
}