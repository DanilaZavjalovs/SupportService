package org.example.supportservice.supportAgent.statistics.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SupportAgentStatisticsDto {
    private UUID id;

    private UUID supportAgent_id;
    private int tickets_handled;
    private float average_response_time;
    private float average_resolution_time;
    private float score;
}
