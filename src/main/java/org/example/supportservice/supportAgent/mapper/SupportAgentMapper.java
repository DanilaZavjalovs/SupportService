package org.example.supportservice.supportAgent.mapper;

import lombok.AllArgsConstructor;
import org.example.supportservice.supportAgent.dto.SupportAgentDto;
import org.example.supportservice.supportAgent.entity.SupportAgentEntity;
import org.example.supportservice.supportAgent.statistics.dto.SupportAgentStatisticsDto;
import org.example.supportservice.supportAgent.statistics.entity.SupportAgentStatisticsEntity;
import org.example.supportservice.supportAgent.statistics.mapper.SupportAgentStatisticsMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SupportAgentMapper {
    private final SupportAgentStatisticsMapper supportAgentStatisticsMapper;

    public SupportAgentEntity dtoToEntity(SupportAgentDto supportAgentDto) {
        SupportAgentEntity supportAgentEntity = new SupportAgentEntity();

        supportAgentEntity.setId(supportAgentDto.getId());
        supportAgentEntity.setFirstname(supportAgentDto.getFirstname());
        supportAgentEntity.setLastname(supportAgentDto.getLastname());
        supportAgentEntity.setEmail(supportAgentDto.getEmail());
        supportAgentEntity.setPhone(supportAgentDto.getPhone());

        if (supportAgentDto.getSupportAgentStatistics() != null) {
            SupportAgentStatisticsEntity statisticsEntity = supportAgentStatisticsMapper.dtoToEntity(supportAgentDto.getSupportAgentStatistics());
            statisticsEntity.setSupportAgent(supportAgentEntity);
            supportAgentEntity.setSupportAgentStatistics(statisticsEntity);
        }

        return supportAgentEntity;
    }

    public SupportAgentDto entityToDto(SupportAgentEntity supportAgentEntity) {
        SupportAgentDto supportAgentDto = new SupportAgentDto();

        supportAgentDto.setId(supportAgentEntity.getId());
        supportAgentDto.setFirstname(supportAgentEntity.getFirstname());
        supportAgentDto.setLastname(supportAgentEntity.getLastname());
        supportAgentDto.setEmail(supportAgentEntity.getEmail());
        supportAgentDto.setPhone(supportAgentEntity.getPhone());

        if (supportAgentEntity.getSupportAgentStatistics() != null) {
            SupportAgentStatisticsDto statisticsDto = supportAgentStatisticsMapper.entityToDto(supportAgentEntity.getSupportAgentStatistics());
            supportAgentDto.setSupportAgentStatistics(statisticsDto);
        }

        return supportAgentDto;
    }
}
