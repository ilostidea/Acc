package com.bit.acc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.acc.model.AnswerCollected;

public interface AnswerCollectedRepository extends JpaRepository<AnswerCollected, Long> {
	
	public List<AnswerCollected> findByUserId( Long userId );

}
