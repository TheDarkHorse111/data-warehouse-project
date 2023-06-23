package com.suliman.datawarehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suliman.datawarehouse.entity.Deal;

@Repository("DealRepository")
public interface DealRepository extends JpaRepository<Deal, Long> {

}
