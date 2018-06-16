package com.bit.acc.dao;

import com.bit.acc.model.AnswerApproved;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerApprovedRepository extends JpaRepository<AnswerApproved, Long> {

	public List<AnswerApproved> findByUserId(Long userId);

	public List<AnswerApproved> findByUserIdAndAnswerId(Long userId, Long answerId);

	public void deleteAllByAnswerIdAndUserId(Long answerId, Long userId);

}
