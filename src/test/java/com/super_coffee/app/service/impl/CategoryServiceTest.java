package com.super_coffee.app.service.impl;

import com.super_coffee.app.models.domain.Category;
import com.super_coffee.app.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith( MockitoExtension.class )
class CategoryServiceTest
{
    @Mock
    private ICategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;
    private Category category;
    private List<Category> categoryList;

    @BeforeEach
    void setUp()
    {
        this.category = new Category();
        this.category.set_id( "CAT1" );
        this.category.setName( "LATTE" );
        this.categoryList = Collections.singletonList( this.category );

        mock( Category.class );
        mock( ICategoryRepository.class );
    }

    @Test
    void testSave()
    {
        when( this.categoryRepository.save( this.category ) ).thenReturn( this.category );
        Category newCategory = this.categoryService.save( this.category );

        assertThat( newCategory.getName() ).isEqualTo( this.category.getName() );
        assertThat( newCategory.getName() ).isNotEmpty();
    }

    @Test
    void testFindAll()
    {
        when( this.categoryRepository.findAllByStatusTrue() ).thenReturn( this.categoryList );
        List<Category> allCategory = this.categoryService.findAll();

        assertThat( allCategory.size() == 1 ).isTrue();
        assertThat( allCategory.isEmpty() ).isFalse();
        assertThat( allCategory.get(0).getName() ).isEqualTo( this.category.getName() );
    }

    @Test
    void testFindById()
    {
        when( this.categoryRepository.findById( "CAT1" ) ).thenReturn( Optional.ofNullable( this.category ) );
        Category categoryFound = this.categoryService.findById( "CAT1" );

        assertThat( categoryFound.getName() ).isNotEmpty();
        assertThat( categoryFound.get_id() ).isEqualTo( this.category.get_id() );
        assertThat( categoryFound.getName() ).isEqualTo( this.category.getName() );
    }

    @Test
    void testCountAllDocuments()
    {
        when( this.categoryService.countAllDocuments() ).thenReturn( 5 );
        int totalCategory = this.categoryService.countAllDocuments();
        assertThat( totalCategory == 5 ).isTrue();
        assertThat( totalCategory ).isNotZero();
    }
}