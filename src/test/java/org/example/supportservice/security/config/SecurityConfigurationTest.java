package org.example.supportservice.security.config;

import org.example.supportservice.supportAgent.dto.SupportAgentDto;
import org.example.supportservice.supportAgent.service.SupportAgentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class SecurityConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupportAgentService supportAgentService;

    private SupportAgentDto supportAgentDto;

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("integration-tests-db")
            .withUsername("username")
            .withPassword("password");

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @BeforeEach
    void setUp() {
        supportAgentDto = new SupportAgentDto();
        supportAgentDto.setId(UUID.randomUUID());
        supportAgentDto.setFirstname("Test");
        supportAgentDto.setLastname("Agent");
        supportAgentDto.setEmail("test.agent@example.com");
        supportAgentDto.setPhone("1234567890");
    }

    @Test
    @WithMockUser(username = "user", roles = {"User"})
    void whenAuthenticatedUser_thenReturns200() throws Exception {
        when(supportAgentService.getSupportAgentById(supportAgentDto.getId())).thenReturn(ResponseEntity.ok(supportAgentDto));

        mockMvc.perform(get("/api/supportAgent/" + supportAgentDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenNoAuthenticatedUser_thenReturns401() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/supportAgent/" + supportAgentDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

        assertEquals(HttpStatus.UNAUTHORIZED.value(), result.getResponse().getStatus());
    }
}