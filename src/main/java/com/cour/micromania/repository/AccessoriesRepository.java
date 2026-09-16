package com.cour.micromania.repository;

import com.cour.micromania.model.Accessories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoriesRepository extends JpaRepository<Accessories, Integer> {

}
