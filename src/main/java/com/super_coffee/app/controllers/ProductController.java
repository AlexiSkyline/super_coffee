package com.super_coffee.app.controllers;

import com.super_coffee.app.models.domain.Product;
import com.super_coffee.app.models.response.ResponseAction;
import com.super_coffee.app.models.response.ResponseGet;
import com.super_coffee.app.response.ResponseHandler;
import com.super_coffee.app.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping( "/api/v1/product")
@AllArgsConstructor
public class ProductController
{
    private final IProductService productService;

    @PostMapping
    public ResponseEntity<ResponseAction> createProduct( @RequestBody @Valid Product product )
    {
        return ResponseHandler.responseBuild( CREATED, "Product Created Successfully", this.productService.save( product ) );
    }

    @GetMapping
    public ResponseEntity<ResponseGet> getAllProduct()
    {
        return ResponseHandler.responseBuild( OK, "Requested All Product are given here", this.productService.countAllDocuments(),
                this.productService.findAll() );
    }

    @GetMapping( "{id}" )
    public ResponseEntity<ResponseAction> getProductById( @PathVariable String id )
    {
        return ResponseHandler.responseBuild( OK, "Requested Product By ID are given here", this.productService.findById( id ) );
    }

    @PutMapping( "{id}" )
    public ResponseEntity<ResponseAction> updateProduct( @PathVariable String id, @RequestBody @Valid Product product )
    {
        return ResponseHandler.responseBuild( OK, "Product Update Successfully", this.productService.update( id, product ) );
    }

    @DeleteMapping( "{id}" )
    public ResponseEntity<ResponseAction> deleteProduct( @PathVariable String id )
    {
        return ResponseHandler.responseBuild( OK, "Product Delete Successfully", this.productService.delete( id ) );
    }
}