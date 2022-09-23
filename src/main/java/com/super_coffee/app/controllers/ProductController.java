package com.super_coffee.app.controllers;

import com.super_coffee.app.models.domain.Product;
import com.super_coffee.app.response.ResponseHandler;
import com.super_coffee.app.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping( "/api/v1/product")
@AllArgsConstructor
public class ProductController
{
    private final IProductService productService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createProduct( @RequestBody @Valid Product product )
    {
        return ResponseHandler.responseBuild( CREATED, "Product Created Successfully", 0,
                this.productService.save( product ) );
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProduct()
    {
        return ResponseHandler.responseBuild( OK, "Requested All Product are given here", this.productService.countAllDocuments(),
                this.productService.findAll() );
    }

    @GetMapping( "{id}" )
    public ResponseEntity<Map<String, Object>> getProductById( @PathVariable String id )
    {
        return ResponseHandler.responseBuild( OK, "Requested Product By ID are given here", 0,
                this.productService.findById( id ) );
    }

    @PutMapping( "{id}" )
    public ResponseEntity<Map<String, Object>> updateProduct( @PathVariable String id, @RequestBody Product product )
    {
        return ResponseHandler.responseBuild( OK, "Product Update Successfully", 0,
                this.productService.update( id, product ) );
    }

    @DeleteMapping( "{id}" )
    public ResponseEntity<Map<String, Object>> deleteProduct( @PathVariable String id )
    {
        return ResponseHandler.responseBuild( OK, "Product Delete Successfully", 0,
                this.productService.delete( id ) );
    }
}