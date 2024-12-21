package org.rohit.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.rohit.entity.Product;

@ApplicationScoped
public class ProductRepository implements PanacheRepositoryBase<Product, Integer> {


}
