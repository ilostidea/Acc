package com.bit.acc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.acc.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
