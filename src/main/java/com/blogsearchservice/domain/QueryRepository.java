package com.blogsearchservice.domain;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface QueryRepository extends JpaRepository<QueryHistory, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<QueryHistory> findByQuery(String query);

    List<QueryHistory> findTop10ByOrderByQueryCountDesc();
}
