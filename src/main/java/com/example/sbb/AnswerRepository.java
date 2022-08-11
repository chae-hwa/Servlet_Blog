package com.example.sbb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Transactional
    @Modifying
    @Query(value = "truncate answer", nativeQuery = true)
    void truncate();
}