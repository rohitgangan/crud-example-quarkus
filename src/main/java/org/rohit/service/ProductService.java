package org.rohit.service;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.rohit.entity.Product;
import org.rohit.repository.ProductRepository;

import java.util.List;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@ApplicationScoped
@WithTransaction
public class ProductService{

@Inject
ProductRepository productRepository;


public Uni<Product> addProduct(Product product){
	return productRepository.persist(product);
}


public Uni<Product> getProductById(Integer id){
    return  productRepository.findById(id);
}


public  Uni<List<Product>> getAllProducts(){

    return productRepository.listAll();

    }

    public Uni<Product> updateProduct(Product product) {
        return productRepository.findById(product.id)
                .onItem().ifNotNull().transformToUni(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setQuantity(product.getQuantity());
                    return productRepository.persistAndFlush(existingProduct);
                }).onItem().ifNull().failWith(new WebApplicationException(NOT_FOUND));
    }


    public Uni<Boolean> deleteProduct(Integer id) {
        return productRepository.findById(id)
                .onItem().ifNotNull().transformToUni(
                        existingProduct ->  productRepository.deleteById(id))
                .onItem().ifNull().failWith(new WebApplicationException(NOT_FOUND));
     }

    public  Uni<List<Product>> getAllProductswithSortByPrice(){

        return productRepository.listAll(Sort.by("price"));

    }

    public Uni<Boolean> getProductQuantitybyId(Integer id, Integer quantity){
        return productRepository.findById(id)
                .onItem().ifNotNull().transformToUni(product -> {
                    if(product.getQuantity() >= quantity){
                        return Uni.createFrom().item(true);
                    }else{
                        return Uni.createFrom().item(false);
                    }
                }).onItem().ifNull().failWith(new WebApplicationException(NOT_FOUND));
    }


}
