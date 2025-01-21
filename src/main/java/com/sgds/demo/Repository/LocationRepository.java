package com.sgds.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgds.demo.model.Location; 

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
