package org.example.supportservice.supportAgent.dto;

import lombok.Data;
import org.example.supportservice.supportAgent.statistics.dto.SupportAgentStatisticsDto;

import java.util.UUID;

@Data
public class SupportAgentDto {
    private UUID id;

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private SupportAgentStatisticsDto supportAgentStatistics;
}
