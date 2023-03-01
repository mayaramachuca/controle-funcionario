package com.projeto.funcionariocontrole.app;

import com.projeto.funcionariocontrole.app.dto.request.FuncionarioRequest;
import com.projeto.funcionariocontrole.app.dto.response.FuncionarioResponse;
import com.projeto.funcionariocontrole.core.mapper.FuncionarioMapper;
import com.projeto.funcionariocontrole.domain.constant.StatusFuncionario;
import com.projeto.funcionariocontrole.domain.entities.Departamento;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import com.projeto.funcionariocontrole.domain.exception.CpfJaRegistradoException;
import com.projeto.funcionariocontrole.domain.exception.FuncionarioNaoEncontradoException;
import com.projeto.funcionariocontrole.domain.exception.OrcamentoInsuficienteException;
import com.projeto.funcionariocontrole.domain.service.FuncionarioService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static com.projeto.funcionariocontrole.utils.JsonConvertionUtils.asJsonString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(MockitoJUnitRunner.class)
public class FuncionarioControllerTest {

    private final Long ID = 1L;
    private MockMvc mockMvc;
    @Mock
    private FuncionarioService service;

    @Mock
    private FuncionarioMapper funcionarioMapper;

    @InjectMocks
    private FuncionarioController controller;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void getAllFuncionariosIsOk() throws Exception {
        Funcionario funcionario = Funcionario
                .builder()
                .cpf("12345678900")
                .dataAniversario("27/02/2023")
                .nome("jose")
                .salario(BigDecimal.valueOf(1000.00))
                .build();
        FuncionarioResponse funcionarioResponse = FuncionarioResponse
                .builder()
                .cpf("12345678900")
                .id(1l)
                .dataAniversario("27/02/2023")
                .nome("jose")
                .salario(BigDecimal.valueOf(1000.00))
                .status(StatusFuncionario.ATIVO)
                .idDepartamento(1l)
                .build();
        when(service.getAllFuncionarios()).thenReturn(Arrays.asList(funcionario));
        when(funcionarioMapper.toListDto(anyList())).thenReturn(Arrays.asList(funcionarioResponse));

        mockMvc.perform(get("/funcionarios/todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(funcionarioResponse.getId()))
                .andExpect(jsonPath("$[0].cpf").value(funcionarioResponse.getCpf()))
                .andExpect(jsonPath("$[0].nome").value(funcionarioResponse.getNome()))
                .andExpect(jsonPath("$[0].dataAniversario").value(funcionarioResponse.getDataAniversario()))
                .andExpect(jsonPath("$[0].salario").value(funcionarioResponse.getSalario()))
                .andExpect(jsonPath("$[0].status").value(funcionarioResponse.getStatus().toString()))
                .andExpect(jsonPath("$[0].idDepartamento").value(funcionarioResponse.getIdDepartamento()))
                .andReturn();
    }

    @Test
    public void getFuncionarioIsOk() throws Exception {
        Funcionario funcionario = Funcionario
                .builder()
                .cpf("12345678900")
                .dataAniversario("27/02/2023")
                .nome("jose")
                .departamento(Departamento.builder().build())
                .id(1l)
                .status(StatusFuncionario.ATIVO)
                .dataRejeicao(LocalDate.now())
                .salario(BigDecimal.valueOf(1000.00))
                .build();
        FuncionarioResponse funcionarioResponse = FuncionarioResponse
                .builder()
                .cpf("12345678900")
                .id(1l)
                .dataAniversario("27/02/2023")
                .nome("jose")
                .salario(BigDecimal.valueOf(1000.00))
                .status(StatusFuncionario.ATIVO)
                .idDepartamento(1l)
                .build();
        when(service.getFuncionarioId(anyLong())).thenReturn(Optional.of(funcionario));
        when(funcionarioMapper.toDto(funcionario)).thenReturn(funcionarioResponse);

        mockMvc.perform(get("/funcionarios/funcionarioid/" + ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(funcionarioResponse.getId()))
                .andExpect(jsonPath("$.cpf").value(funcionarioResponse.getCpf()))
                .andExpect(jsonPath("$.nome").value(funcionarioResponse.getNome()))
                .andExpect(jsonPath("$.dataAniversario").value(funcionarioResponse.getDataAniversario()))
                .andExpect(jsonPath("$.salario").value(funcionarioResponse.getSalario()))
                .andExpect(jsonPath("$.status").value(funcionarioResponse.getStatus().toString()))
                .andExpect(jsonPath("$.idDepartamento").value(funcionarioResponse.getIdDepartamento()))
                .andReturn();
    }

    @Test
    public void getFuncionarioNotOk() throws Exception {

        doThrow(FuncionarioNaoEncontradoException.class).when(service).getFuncionarioId(anyLong());

        mockMvc.perform(get("/funcionarios/funcionarioid/" + ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void createFuncionarioIsOk() throws Exception {
        Funcionario funcionario = Funcionario
                .builder()
                .cpf("12345678900")
                .dataAniversario("27/02/2023")
                .nome("jose")
                .departamento(Departamento.builder().build())
                .id(1l)
                .status(StatusFuncionario.ATIVO)
                .dataRejeicao(LocalDate.now())
                .salario(BigDecimal.valueOf(1000.00))
                .build();

        FuncionarioRequest funcionarioRequest = FuncionarioRequest
                .builder()
                .cpf("36974253826")
                .dataAniversario("27/02/2023")
                .nome("jose")
                .salario(BigDecimal.valueOf(1000.00))
                .idDepartamento(1l)
                .build();

        FuncionarioResponse funcionarioResponse = FuncionarioResponse
                .builder()
                .id(1l)
                .cpf("12345678900")
                .dataAniversario("27/02/2023")
                .nome("jose")
                .salario(BigDecimal.valueOf(1000.00))
                .idDepartamento(1l)
                .status(StatusFuncionario.ATIVO)
                .build();

        when(service.save(funcionario)).thenReturn(funcionario);
        when(funcionarioMapper.toEntity(funcionarioRequest)).thenReturn(funcionario);
        when(funcionarioMapper.toDto(funcionario)).thenReturn(funcionarioResponse);

        var content = asJsonString(funcionarioRequest);
        mockMvc.perform(post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(funcionarioResponse.getId()))
                .andExpect(jsonPath("$.cpf").value(funcionarioResponse.getCpf()))
                .andExpect(jsonPath("$.nome").value(funcionarioResponse.getNome()))
                .andExpect(jsonPath("$.dataAniversario").value(funcionarioResponse.getDataAniversario()))
                .andExpect(jsonPath("$.salario").value(funcionarioResponse.getSalario()))
                .andExpect(jsonPath("$.status").value(funcionarioResponse.getStatus().toString()))
                .andExpect(jsonPath("$.idDepartamento").value(funcionarioResponse.getIdDepartamento()));
    }

    @Test
    public void createFuncionarioIdNotOk() throws Exception {
        Funcionario funcionario = Funcionario
                .builder()
                .cpf("12345678900")
                .dataAniversario("27/02/2023")
                .nome("jose")
                .departamento(Departamento.builder().build())
                .id(1l)
                .status(StatusFuncionario.ATIVO)
                .dataRejeicao(LocalDate.now())
                .salario(BigDecimal.valueOf(1000.00))
                .build();

        doThrow(CpfJaRegistradoException.class).when(service).save(funcionario);

        mockMvc.perform(post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void aumentoSalarioIsOk() throws Exception {
        Funcionario funcionario = Funcionario
                .builder()
                .cpf("36974253826")
                .dataAniversario("27/02/2023")
                .nome("jose")
                .departamento(Departamento
                        .builder()
                        .budget(BigDecimal.valueOf(3000.00))
                        .totalSalarios(BigDecimal.TEN)
                        .id(1l)
                        .build())
                .id(1l)
                .status(StatusFuncionario.ATIVO)
                .dataRejeicao(LocalDate.now())
                .salario(BigDecimal.valueOf(1000.00))
                .build();

        FuncionarioResponse funcionarioResponse = FuncionarioResponse
                .builder()
                .id(1l)
                .cpf("36974253826")
                .dataAniversario("27/02/2023")
                .nome("jose")
                .salario(BigDecimal.valueOf(1500.00))
                .idDepartamento(1l)
                .status(StatusFuncionario.ATIVO)
                .build();

        when(service.aumentoSalario(any(),any())).thenReturn(funcionario);
        when(funcionarioMapper.toDto(any())).thenReturn(funcionarioResponse);

        mockMvc.perform(put("/funcionarios/aumento/"+ ID.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BigDecimal.valueOf(500.00).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(funcionarioResponse.getId()))
                .andExpect(jsonPath("$.cpf").value(funcionarioResponse.getCpf()))
                .andExpect(jsonPath("$.nome").value(funcionarioResponse.getNome()))
                .andExpect(jsonPath("$.dataAniversario").value(funcionarioResponse.getDataAniversario()))
                .andExpect(jsonPath("$.salario").value(funcionarioResponse.getSalario()))
                .andExpect(jsonPath("$.status").value(funcionarioResponse.getStatus().toString()))
                .andExpect(jsonPath("$.idDepartamento").value(funcionarioResponse.getIdDepartamento()))
                .andReturn();
    }

    @Test
    public void aumentoSalarioIsBadRequestOrcamento() throws Exception {

        doThrow(OrcamentoInsuficienteException.class).when(service).aumentoSalario(any(),any());
        mockMvc.perform(put("/funcionarios/aumento/" + ID.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
