package com.doubleDB.project.repository2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doubleDB.project.entity.ResponseEntity;

@Repository
public interface UserRepository2 extends JpaRepository<ResponseEntity, Long> {

}
