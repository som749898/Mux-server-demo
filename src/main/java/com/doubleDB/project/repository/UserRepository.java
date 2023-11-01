package com.doubleDB.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doubleDB.project.entity.ResponseEntity;

@Repository
public interface UserRepository extends JpaRepository<ResponseEntity, Long>  {

}
