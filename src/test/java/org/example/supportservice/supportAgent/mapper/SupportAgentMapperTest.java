package org.example.supportservice.supportAgent.mapper;

import org.example.supportservice.supportAgent.dto.SupportAgentDto;
import org.example.supportservice.supportAgent.entity.SupportAgentEntity;
import org.example.supportservice.supportAgent.statistics.dto.SupportAgentStatisticsDto;
import org.example.supportservice.supportAgent.statistics.entity.SupportAgentStatisticsEntity;
import org.example.supportservice.supportAgent.statistics.mapper.SupportAgentStatisticsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupportAgentMapperTest {

    private SupportAgentMapper supportAgentMapper;
    private SupportAgentStatisticsMapper supportAgentStatisticsMapper;

    @BeforeEach
    void setUp() {
        supportAgentStatisticsMapper = new SupportAgentStatisticsMapper();
        supportAgentMapper = new SupportAgentMapper(supportAgentStatisticsMapper);
    }

    @Test
    void itShouldDtoToEntity() {
        SupportAgentDto supportAgentDto = new SupportAgentDto();
        supportAgentDto.setId(UUID.randomUUID());
        supportAgentDto.setFirstname("John");
        supportAgentDto.setLastname("Doe");
        supportAgentDto.setEmail("john.doe@example.com");
        supportAgentDto.setPhone("1234567890");

        SupportAgentStatisticsDto statisticsDto = new SupportAgentStatisticsDto();
        statisticsDto.setId(UUID.randomUUID());
        statisticsDto.setSupportAgent_id(supportAgentDto.getId());
        statisticsDto.setTickets_handled(10);
        statisticsDto.setAverage_response_time(1.5f);
        statisticsDto.setAverage_resolution_time(2.5f);
        statisticsDto.setScore(4.5f);

        supportAgentDto.setSupportAgentStatistics(statisticsDto);

        SupportAgentEntity supportAgentEntity = supportAgentMapper.dtoToEntity(supportAgentDto);

        assertEquals(supportAgentDto.getId(), supportAgentEntity.getId());
        assertEquals(supportAgentDto.getFirstname(), supportAgentEntity.getFirstname());
        assertEquals(supportAgentDto.getLastname(), supportAgentEntity.getLastname());
        assertEquals(supportAgentDto.getEmail(), supportAgentEntity.getEmail());
        assertEquals(supportAgentDto.getPhone(), supportAgentEntity.getPhone());
        assertEquals(supportAgentDto.getSupportAgentStatistics().getId(), supportAgentEntity.getSupportAgentStatistics().getId());
    }

    @Test
    void itShouldEntityToDto() {
        SupportAgentEntity supportAgentEntity = new SupportAgentEntity();
        supportAgentEntity.setId(UUID.randomUUID());
        supportAgentEntity.setFirstname("John");
        supportAgentEntity.setLastname("Doe");
        supportAgentEntity.setEmail("john.doe@example.com");
        supportAgentEntity.setPhone("1234567890");

        SupportAgentStatisticsEntity statisticsEntity = new SupportAgentStatisticsEntity();
        statisticsEntity.setId(UUID.randomUUID());
        statisticsEntity.setSupportAgent(supportAgentEntity);
        statisticsEntity.setTickets_handled(10);
        statisticsEntity.setAverage_response_time(1.5f);
        statisticsEntity.setAverage_resolution_time(2.5f);
        statisticsEntity.setScore(4.5f);

        supportAgentEntity.setSupportAgentStatistics(statisticsEntity);

        SupportAgentDto supportAgentDto = supportAgentMapper.entityToDto(supportAgentEntity);

        assertEquals(supportAgentEntity.getId(), supportAgentDto.getId());
        assertEquals(supportAgentEntity.getFirstname(), supportAgentDto.getFirstname());
        assertEquals(supportAgentEntity.getLastname(), supportAgentDto.getLastname());
        assertEquals(supportAgentEntity.getEmail(), supportAgentDto.getEmail());
        assertEquals(supportAgentEntity.getPhone(), supportAgentDto.getPhone());
        assertEquals(supportAgentEntity.getSupportAgentStatistics().getId(), supportAgentDto.getSupportAgentStatistics().getId());
    }
}