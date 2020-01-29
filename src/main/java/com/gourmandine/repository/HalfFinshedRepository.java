package com.gourmandine.repository;

import com.gourmandine.entity.HalfFinshed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HalfFinshedRepository  extends JpaRepository<HalfFinshed, Long> {
}
