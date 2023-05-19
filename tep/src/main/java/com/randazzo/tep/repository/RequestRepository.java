package com.randazzo.tep.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.randazzo.tep.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
    //Boolean existsByDate(String date);
}
