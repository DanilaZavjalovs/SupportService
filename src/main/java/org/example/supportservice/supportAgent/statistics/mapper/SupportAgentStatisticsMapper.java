package org.example.supportservice.supportAgent.statistics.mapper;

import lombok.AllArgsConstructor;
import org.example.supportservice.supportAgent.statistics.dto.SupportAgentStatisticsDto;
import org.example.supportservice.supportAgent.statistics.entity.SupportAgentStatisticsEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SupportAgentStatisticsMapper {

    public SupportAgentStatisticsDto entityToDto (SupportAgentStatisticsEntity entity) {
        SupportAgentStatisticsDto dto = new SupportAgentStatisticsDto();

        dto.setId(entity.getId());
        dto.setTickets_handled(entity.getTickets_handled());
        dto.setAverage_response_time(entity.getAverage_response_time());
        dto.setAverage_resolution_time(entity.getAverage_resolution_time());
        dto.setScore(entity.getScore());
        dto.setSupportAgent_id(entity.getSupportAgent().getId());

        return dto;
    }

    public SupportAgentStatisticsEntity dtoToEntity (SupportAgentStatisticsDto dto) {
        SupportAgentStatisticsEntity entity = new SupportAgentStatisticsEntity();

        entity.setId(dto.getId());
        entity.setTickets_handled(dto.getTickets_handled());
        entity.setAverage_response_time(dto.getAverage_response_time());
        entity.setAverage_resolution_time(dto.getAverage_resolution_time());
        entity.setScore(dto.getScore());

        return entity;
    }
}
