package com.super_coffee.app.controllers;

import com.super_coffee.app.models.domain.Category;
import com.super_coffee.app.models.response.ResponseAction;
import com.super_coffee.app.models.response.ResponseGet;
import com.super_coffee.app.response.ResponseHandler;
import com.super_coffee.app.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping( "/api/v1/category" )
@AllArgsConstructor
public class CategoryController
{
    private final ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<ResponseAction> createCategory( @RequestBody @Valid Category category )
    {
        return ResponseHandler.responseBuild( CREATED, "Category Created Successfully", this.categoryService.save( category ) );
    }

    @GetMapping
    public ResponseEntity<ResponseGet> getAllCategory()
    {
        return ResponseHandler.responseBuild( OK, "Requested All Category are given here", this.categoryService.countAllDocuments(),
                                        this.categoryService.findAll() );
    }

    @GetMapping( "{id}" )
    public ResponseEntity<ResponseAction> getCategoryById( @PathVariable String id )
    {
        return ResponseHandler.responseBuild( OK, "Requested Category By ID are given here", this.categoryService.findById( id ) );
    }

    @PutMapping( "{id}" )
    public ResponseEntity<ResponseAction> updateCategory( @PathVariable String id, @RequestBody @Valid Category category )
    {
        return ResponseHandler.responseBuild( OK, "Category Update Successfully", this.categoryService.update( id, category ) );
    }

    @DeleteMapping( "{id}" )
    public ResponseEntity<ResponseAction> deleteCategory( @PathVariable String id )
    {
        return ResponseHandler.responseBuild( OK, "Category Delete Successfully", this.categoryService.delete( id ) );
    }
}