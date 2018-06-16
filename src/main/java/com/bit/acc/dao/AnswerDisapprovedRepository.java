package com.bit.acc.dao;

import com.bit.acc.model.AnswerDisapproved;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerDisapprovedRepository extends JpaRepository<AnswerDisapproved, Long> {

	public List<AnswerDisapproved> findByUserId(Long userId);

	public List<AnswerDisapproved> findByUserIdAndAnswerId(Long userId, Long answerId);

	public void deleteAllByAnswerIdAndUserId(Long answerId, Long userId);

}
