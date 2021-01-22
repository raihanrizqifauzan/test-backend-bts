package com.raihan.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raihan.test.model.Shopping;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Long> {

}
