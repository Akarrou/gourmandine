package com.gourmandine.repository;

import com.gourmandine.entity.FinalProduct;
import com.gourmandine.entity.HalfFinshed;
import com.gourmandine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinalProductRepository extends JpaRepository<FinalProduct, Long> {

    List<FinalProduct> findAllByUserAndStatus(User user, String status);
    List<FinalProduct> findAllByStatus(String status);

}
