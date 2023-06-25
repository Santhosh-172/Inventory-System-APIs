package com.springboot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.model.InwardRegister;
import com.springboot.main.model.OutwardRegister;

public interface OutwardRegisterRepository extends JpaRepository<OutwardRegister, Integer> {

}
