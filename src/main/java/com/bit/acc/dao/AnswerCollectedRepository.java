package com.bit.acc.dao;

import com.bit.acc.model.AnswerCollected;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerCollectedRepository extends JpaRepository<AnswerCollected, Long> {
	
	public List<AnswerCollected> findByUserId( Long userId );

	public void deleteAllByAnswerIdAndUserId(Long answerId, Long userId);

}
