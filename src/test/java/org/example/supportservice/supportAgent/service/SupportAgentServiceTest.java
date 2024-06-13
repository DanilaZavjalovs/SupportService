package org.example.supportservice.supportAgent.service;

import org.example.supportservice.supportAgent.dto.SupportAgentDto;
import org.example.supportservice.supportAgent.entity.SupportAgentEntity;
import org.example.supportservice.supportAgent.mapper.SupportAgentMapper;
import org.example.supportservice.supportAgent.repository.SupportAgentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SupportAgentServiceTest {

    @Mock
    private SupportAgentRepository supportAgentRepository;

    @Mock
    private SupportAgentMapper supportAgentMapper;

    @InjectMocks
    private SupportAgentService supportAgentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void itShouldGetSupportAgentById() {
        UUID id = UUID.randomUUID();
        SupportAgentEntity entity = new SupportAgentEntity();
        SupportAgentDto dto = new SupportAgentDto();

        when(supportAgentRepository.findById(id)).thenReturn(Optional.of(entity));
        when(supportAgentMapper.entityToDto(entity)).thenReturn(dto);

        supportAgentService.getSupportAgentById(id);

        verify(supportAgentRepository).findById(id);
        verify(supportAgentMapper).entityToDto(entity);
    }

    @Test
    void itShouldGetSupportAgentsWithStats() {
        SupportAgentEntity entity = new SupportAgentEntity();
        SupportAgentDto dto = new SupportAgentDto();

        when(supportAgentRepository.findAll()).thenReturn(Collections.singletonList(entity));
        when(supportAgentMapper.entityToDto(entity)).thenReturn(dto);

        supportAgentService.getSupportAgents(true);

        verify(supportAgentRepository).findAll();
        verify(supportAgentMapper).entityToDto(entity);
    }

    @Test
    void itShouldGetSupportAgentsWithoutStats() {
        SupportAgentEntity entity = new SupportAgentEntity();
        SupportAgentDto dto = new SupportAgentDto();
        dto.setSupportAgentStatistics(null);

        when(supportAgentRepository.findAll()).thenReturn(Collections.singletonList(entity));
        when(supportAgentMapper.entityToDto(entity)).thenReturn(dto);

        supportAgentService.getSupportAgents(false);

        verify(supportAgentRepository).findAll();
        verify(supportAgentMapper).entityToDto(entity);
    }

    @Test
    void itShouldCreateSupportAgent() {
        SupportAgentDto dto = new SupportAgentDto();
        SupportAgentEntity entity = new SupportAgentEntity();

        when(supportAgentMapper.dtoToEntity(dto)).thenReturn(entity);
        when(supportAgentRepository.save(entity)).thenReturn(entity);
        when(supportAgentMapper.entityToDto(entity)).thenReturn(dto);

        supportAgentService.createSupportAgent(dto);

        verify(supportAgentMapper).dtoToEntity(dto);
        verify(supportAgentRepository).save(entity);
        verify(supportAgentMapper).entityToDto(entity);
    }

    @Test
    void itShouldUpdateSupportAgent() {
        SupportAgentDto dto = new SupportAgentDto();
        SupportAgentEntity entity = new SupportAgentEntity();
        entity.setId(UUID.randomUUID());

        when(supportAgentMapper.dtoToEntity(dto)).thenReturn(entity);
        when(supportAgentRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(supportAgentRepository.save(entity)).thenReturn(entity);
        when(supportAgentMapper.entityToDto(entity)).thenReturn(dto);

        supportAgentService.updateSupportAgent(dto);

        verify(supportAgentMapper).dtoToEntity(dto);
        verify(supportAgentRepository).findById(entity.getId());
        verify(supportAgentRepository).save(entity);
        verify(supportAgentMapper).entityToDto(entity);
    }
}