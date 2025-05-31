package com.example.interstellarmission.repository;

import com.example.interstellarmission.model.entity.MissionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissionLogRepository extends JpaRepository<MissionLog, Long> {
    Optional<MissionLog> findByIdAndTitle(Long id, String title);
    List<MissionLog> findByTitle(String title);
}
