package com.gourmandine.repository;

import com.gourmandine.entity.FinalProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalProductRepository extends JpaRepository<FinalProduct, Long> {

}
