package com.mustache.bbs.repository;

import com.mustache.bbs.Domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital,Integer> {
}
