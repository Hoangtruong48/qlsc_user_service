package com.example.quanlysancau.repo;

import com.example.quanlysancau.entity.BadmintonCourt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadmintonCourtRepository extends JpaRepository<BadmintonCourt, Integer> {
}
