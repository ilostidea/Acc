package com.bit.acc.dao;

import com.bit.acc.model.QuestionDisapproved;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionDisapprovedRepository extends JpaRepository<QuestionDisapproved, Long> {

	public List<QuestionDisapproved> findByUserId(Long userId);

	public List<QuestionDisapproved> findByUserIdAndQuestionId(Long userId, Long questionId);

	public void deleteAllByQuestionIdAndUserId(Long questionId, Long userId);

}
