package com.bit.acc.dao;

import com.bit.acc.model.QuestionApproved;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionApprovedRepository extends JpaRepository<QuestionApproved, Long> {

	public List<QuestionApproved> findByUserId(Long userId);

	public List<QuestionApproved> findByUserIdAndQuestionId(Long userId, Long questionId);

	public void deleteAllByQuestionIdAndUserId(Long questionId, Long userId);

}
