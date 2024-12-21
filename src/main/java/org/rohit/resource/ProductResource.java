package org.rohit.resource;


import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.rohit.entity.Product;
import org.rohit.service.ProductService;

import java.util.List;

@Path("product")
@Produces("application/json")
@Consumes("application/json")
public class ProductResource {

    @Inject
    ProductService productService;


    @GET
    public Uni<RestResponse<List<Product>>> getAllProducts(){
        return productService.getAllProducts()
                .map(products -> RestResponse.status(Response.Status.OK, products));
    }

    @POST
    public Uni<RestResponse<Product>> addProduct(Product product){
        return productService.addProduct(product)
                .map(entity -> RestResponse.status(Response.Status.CREATED, entity));
    }

    @PUT
    public Uni<RestResponse<?>> updateProduct(Product product)
    {

        return productService.updateProduct(product)
                .map(productUpdate  -> RestResponse.status(Response.Status.OK, productUpdate));

    }

    @DELETE
    @Path("{id}")
    public Uni<RestResponse<Boolean>> deleteProduct(@PathParam("id") Integer id){
        return productService.deleteProduct(id)
                .map(deleted -> RestResponse.status(Response.Status.NO_CONTENT, deleted));
    }

    @GET
    @Path("{id}")
    public Uni<RestResponse<Product>> getProductById(@PathParam("id") Integer id){
        return productService.getProductById(id)
                .map(product -> RestResponse.status(Response.Status.OK, product));
    }

    @GET
    @Path("sortbyprice")
    public Uni<RestResponse<List<Product>>> getAllProductswithSortByPrice(){
        return productService.getAllProductswithSortByPrice()
                .map(products -> RestResponse.status(Response.Status.OK, products));
    }

    @GET
    @Path("quantity/{id}/{quantity}")
    public Uni<RestResponse<Boolean>> getProductQuantitybyId(@PathParam("id") Integer id, @PathParam("quantity") Integer quantity){
        return productService.getProductQuantitybyId(id, quantity)
                .map(product -> RestResponse.status(Response.Status.OK, product));
    }



}
