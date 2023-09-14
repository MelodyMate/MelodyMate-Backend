package com.melodymatebackend.repository;

import com.melodymatebackend.domain.Streaminghistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamingHistoryRepository extends JpaRepository<Streaminghistory, Long> {
}
