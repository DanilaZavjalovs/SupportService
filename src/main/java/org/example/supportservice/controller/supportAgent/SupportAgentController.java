package org.example.supportservice.controller.supportAgent;

import lombok.AllArgsConstructor;
import org.example.supportservice.supportAgent.dto.SupportAgentDto;
import org.example.supportservice.supportAgent.service.SupportAgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/supportAgent")
@AllArgsConstructor
public class SupportAgentController {

    private final SupportAgentService supportAgentService;

    @GetMapping("{id}")
    public ResponseEntity<SupportAgentDto> getSupportAgent(@PathVariable UUID id) {
        return supportAgentService.getSupportAgentById(id);
    }

    @GetMapping
    public ResponseEntity<List<SupportAgentDto>> getSupportAgents(@RequestParam(defaultValue = "true") boolean includeStats) {
        return supportAgentService.getSupportAgents(includeStats);
    }

    @PostMapping
    public ResponseEntity<SupportAgentDto> createSupportAgent(@RequestBody SupportAgentDto supportAgentDto) {
        return supportAgentService.createSupportAgent(supportAgentDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<SupportAgentDto> updateSupportAgent(@PathVariable UUID id, @RequestBody SupportAgentDto supportAgentDto) {
        supportAgentDto.setId(id);

        SupportAgentDto updatedSupportAgentDto = supportAgentService.updateSupportAgent(supportAgentDto);
        return ResponseEntity.ok(updatedSupportAgentDto);
    }
}

