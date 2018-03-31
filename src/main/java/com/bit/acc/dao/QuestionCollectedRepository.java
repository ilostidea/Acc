package com.bit.acc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.acc.model.QuestionCollected;

public interface QuestionCollectedRepository extends JpaRepository<QuestionCollected, Long> {
	
	public List<QuestionCollected> findByUserId(Long userId);

}
