package com.gourmandine.repository;

import com.gourmandine.entity.FinalProduct;
import com.gourmandine.entity.HalfFinshed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HalfFinshedRepository  extends JpaRepository<HalfFinshed, Long> {

   List<HalfFinshed> findAllByType(String type);

}
