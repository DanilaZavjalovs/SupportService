package org.example.supportservice.supportAgent.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.supportservice.supportAgent.dto.SupportAgentDto;
import org.example.supportservice.supportAgent.entity.SupportAgentEntity;
import org.example.supportservice.supportAgent.mapper.SupportAgentMapper;
import org.example.supportservice.supportAgent.repository.SupportAgentRepository;
import org.example.supportservice.supportAgent.statistics.entity.SupportAgentStatisticsEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SupportAgentService {
    private final SupportAgentRepository supportAgentRepository;
    private final SupportAgentMapper supportAgentMapper;

    public ResponseEntity<SupportAgentDto> getSupportAgentById(UUID id) {
        SupportAgentDto dto = supportAgentMapper.entityToDto(
                supportAgentRepository.findById(id).orElseThrow(()
                        -> new NoSuchElementException("No Support Agent with id " + id))
        );

        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<List<SupportAgentDto>> getSupportAgents(boolean includeStats) {
        List<SupportAgentDto> list;

        if (includeStats) {
            list = supportAgentRepository.findAll().stream()
                    .map(supportAgentMapper::entityToDto).toList();
        } else {
            list = supportAgentRepository.findAll().stream()
                    .map(agent -> {
                        SupportAgentDto dto = supportAgentMapper.entityToDto(agent);
                        dto.setSupportAgentStatistics(null);
                        return dto;
                    }).toList();
        }

        return ResponseEntity.ok(list);
    }

    public ResponseEntity<SupportAgentDto> createSupportAgent(SupportAgentDto dto) {

        return ResponseEntity.ok(
                supportAgentMapper.entityToDto(
                        supportAgentRepository.save(
                                supportAgentMapper.dtoToEntity(dto))));
    }

    @Transactional
    public SupportAgentDto updateSupportAgent(SupportAgentDto supportAgentDto) {

        SupportAgentEntity supportAgentEntity = supportAgentMapper.dtoToEntity(supportAgentDto);

        SupportAgentEntity existingSupportAgentEntity = supportAgentRepository.findById(supportAgentEntity.getId())
                .orElseThrow(() -> new EntityNotFoundException("Support Agent not found"));

        updateSupportAgentEntityFields(existingSupportAgentEntity, supportAgentEntity);

        SupportAgentEntity updatedSupportAgentEntity = supportAgentRepository.save(existingSupportAgentEntity);

        return supportAgentMapper.entityToDto(updatedSupportAgentEntity);
    }

    private void updateSupportAgentEntityFields(SupportAgentEntity existingEntity, SupportAgentEntity newEntity) {
        existingEntity.setFirstname(newEntity.getFirstname());
        existingEntity.setLastname(newEntity.getLastname());
        existingEntity.setEmail(newEntity.getEmail());
        existingEntity.setPhone(newEntity.getPhone());

        if (newEntity.getSupportAgentStatistics() != null) {
            SupportAgentStatisticsEntity existingStatisticsEntity = existingEntity.getSupportAgentStatistics();
            SupportAgentStatisticsEntity updatedStatisticsEntity = newEntity.getSupportAgentStatistics();

            existingStatisticsEntity.setTickets_handled(updatedStatisticsEntity.getTickets_handled());
            existingStatisticsEntity.setAverage_response_time(updatedStatisticsEntity.getAverage_response_time());
            existingStatisticsEntity.setAverage_resolution_time(updatedStatisticsEntity.getAverage_resolution_time());
            existingStatisticsEntity.setScore(updatedStatisticsEntity.getScore());
        }
    }
}
