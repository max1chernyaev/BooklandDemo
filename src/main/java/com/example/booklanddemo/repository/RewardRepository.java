package com.example.booklanddemo.repository;

import com.example.booklanddemo.domain.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardRepository extends JpaRepository<Reward, Integer> {
}
