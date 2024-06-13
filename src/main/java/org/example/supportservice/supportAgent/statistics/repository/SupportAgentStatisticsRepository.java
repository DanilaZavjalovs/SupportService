package org.example.supportservice.supportAgent.statistics.repository;

import org.example.supportservice.supportAgent.statistics.entity.SupportAgentStatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupportAgentStatisticsRepository extends JpaRepository<SupportAgentStatisticsEntity, UUID> {
}
