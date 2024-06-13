package org.example.supportservice.supportAgent.repository;

import org.example.supportservice.supportAgent.entity.SupportAgentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupportAgentRepository extends JpaRepository<SupportAgentEntity, UUID> {
}
