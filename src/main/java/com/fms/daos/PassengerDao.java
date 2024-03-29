package com.fms.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fms.dtos.Passenger;

@Repository("pr")
public interface PassengerDao extends JpaRepository<Passenger, Integer> {

}