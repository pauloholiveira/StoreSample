package br.com.paulo.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.paulo.store.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
