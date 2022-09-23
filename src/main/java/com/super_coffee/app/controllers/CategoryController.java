package com.super_coffee.app.controllers;

import com.super_coffee.app.models.domain.Category;
import com.super_coffee.app.response.ResponseHandler;
import com.super_coffee.app.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping( "/api/v1/category" )
@AllArgsConstructor
public class CategoryController
{
    private final ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createCategory( @RequestBody Category category )
    {
        return ResponseHandler.responseBuild( CREATED, "Category Created Successfully", 0,
                                        this.categoryService.save( category ) );
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCategory()
    {
        return ResponseHandler.responseBuild( OK, "Requested All Category are given here", this.categoryService.countCategory(),
                                        this.categoryService.findAll() );
    }

    @GetMapping( "{id}" )
    public ResponseEntity<Map<String, Object>> getCategoryById( @PathVariable String id )
    {
        return ResponseHandler.responseBuild( OK, "Requested Category By ID are given here", 0,
                this.categoryService.findById( id ) );
    }

    @PutMapping( "{id}" )
    public ResponseEntity<Map<String, Object>> updateCategory( @PathVariable String id, @RequestBody Category category )
    {
        return ResponseHandler.responseBuild( OK, "Category Update Successfully", 0,
                this.categoryService.update( id, category ) );
    }

    @DeleteMapping( "{id}" )
    public ResponseEntity<Map<String, Object>> deleteCategory( @PathVariable String id )
    {
        return ResponseHandler.responseBuild( OK, "Category Delete Successfully", 0,
                this.categoryService.delete( id ) );
    }
}