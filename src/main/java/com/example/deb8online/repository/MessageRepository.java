package com.example.deb8online.repository;

import com.example.deb8online.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Message findByMessageUserId(long messageUserId);
}
