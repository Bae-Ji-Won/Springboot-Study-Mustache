package com.mustache.bbs.repository;

import com.mustache.bbs.Domain.Hospital;
import com.mustache.bbs.Domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
