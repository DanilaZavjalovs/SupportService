package org.example.supportservice.supportAgent.statistics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.example.supportservice.supportAgent.entity.SupportAgentEntity;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "supportAgent_Statistics_table")
@Data
public class SupportAgentStatisticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "supportAgent_id")
    private SupportAgentEntity supportAgent;

    @Min(0)
    private int tickets_handled;

    @Min(0)
    private float average_response_time;

    @Min(0)
    private float average_resolution_time;

    @Min(0)
    @Max(5)
    private float score;

    @UpdateTimestamp
    private LocalDate updated;
}
