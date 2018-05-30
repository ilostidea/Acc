package com.bit.acc.dao;

import com.bit.acc.model.QuestionCollected;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionCollectedRepository extends JpaRepository<QuestionCollected, Long> {
	
	public List<QuestionCollected> findByUserId(Long userId);

	public void deleteAllByQuestionIdAndUserId(Long questionId, Long userId);

}
