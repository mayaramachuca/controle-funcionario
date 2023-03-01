package com.projeto.funcionariocontrole.app;

import com.projeto.funcionariocontrole.app.dto.request.DepartamentoRequest;
import com.projeto.funcionariocontrole.app.dto.response.DepartamentoResponse;
import com.projeto.funcionariocontrole.app.dto.response.FuncionarioResponse;
import com.projeto.funcionariocontrole.core.mapper.DepartamentoMapper;
import com.projeto.funcionariocontrole.domain.entities.Departamento;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import com.projeto.funcionariocontrole.domain.exception.DepartamentoJaRegistradoException;
import com.projeto.funcionariocontrole.domain.exception.DepartamentoNaoEncontradoException;
import com.projeto.funcionariocontrole.domain.service.DepartamentoService;
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
import java.util.Arrays;

import static com.projeto.funcionariocontrole.utils.JsonConvertionUtils.asJsonString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class DepartamentoControllerTest  {

    private final Long ID = 1L;
    private MockMvc mockMvc;
    @Mock
    public DepartamentoService service;
    @Mock
    public DepartamentoMapper departamentoMapper;
    @InjectMocks
    public DepartamentoController contoller;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(contoller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void createDepartamentoIsOk() throws Exception {

        Departamento departamento = Departamento
                .builder()
                .id(1l)
                .nome("Departamento1")
                .descricao("Deparatamento1")
                .budget(BigDecimal.valueOf(2000.00))
                .totalSalarios(BigDecimal.ZERO)
                .funcionarios(Arrays.asList(Funcionario.builder().build()))
                .build();

        DepartamentoResponse departamentoResponse = DepartamentoResponse
                .builder()
                .id(1l)
                .nome("Departamento1")
                .descricao("Deparatamento1")
                .budget(BigDecimal.valueOf(2000.00))
                .totalSalarios(BigDecimal.ZERO)
                .funcionarios(Arrays.asList(FuncionarioResponse.builder().build()))
                .build();

        DepartamentoRequest departamentoRequest = DepartamentoRequest
                .builder()
                .nome("Departamento1")
                .descricao("Deparatamento1")
                .budget(BigDecimal.valueOf(2000.00))
                .build();

        when(service.save(departamento)).thenReturn(departamento);
        when(departamentoMapper.toEntity(departamentoRequest)).thenReturn(departamento);
        when(departamentoMapper.toDto(departamento)).thenReturn(departamentoResponse);

        var content = asJsonString(departamentoRequest);
        mockMvc.perform(post("/departamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(departamentoResponse.getId()))
                .andExpect(jsonPath("$.nome").value(departamentoResponse.getNome()))
                .andExpect(jsonPath("$.descricao").value(departamentoResponse.getDescricao()))
                .andExpect(jsonPath("$.budget").value(departamentoResponse.getBudget()))
                .andExpect(jsonPath("$.totalSalarios").value(departamentoResponse.getTotalSalarios()));
    }

    @Test
    public void notCreateDepartamentoNome() throws Exception {
        doThrow(DepartamentoJaRegistradoException.class).when(service).save(any());
        mockMvc.perform(post("/departamentos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getDepartamentoIsOk() throws Exception {
        Departamento departamento = Departamento
                .builder()
                .id(1l)
                .nome("Departamento1")
                .descricao("Deparatamento1")
                .budget(BigDecimal.valueOf(2000.00))
                .totalSalarios(BigDecimal.ZERO)
                .funcionarios(Arrays.asList(Funcionario.builder().build()))
                .build();

        DepartamentoResponse departamentoResponse = DepartamentoResponse
                .builder()
                .id(1l)
                .nome("Departamento1")
                .descricao("Deparatamento1")
                .budget(BigDecimal.valueOf(2000.00))
                .totalSalarios(BigDecimal.ZERO)
                .funcionarios(Arrays.asList(FuncionarioResponse.builder().build()))
                .build();
        when(service.getDepartamentoId(anyLong())).thenReturn(departamento);
        when(departamentoMapper.toDto(departamento)).thenReturn(departamentoResponse);

        mockMvc.perform(get("/departamentos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(departamentoResponse.getId()))
                .andExpect(jsonPath("$.nome").value(departamentoResponse.getNome()))
                .andExpect(jsonPath("$.descricao").value(departamentoResponse.getDescricao()))
                .andExpect(jsonPath("$.budget").value(departamentoResponse.getBudget()))
                .andExpect(jsonPath("$.totalSalarios").value(departamentoResponse.getTotalSalarios()));
    }

    @Test
    public void getDepartamentoNotOk() throws Exception {
        doThrow(DepartamentoNaoEncontradoException.class).when(service).getDepartamentoId(anyLong());

        mockMvc.perform(get("/departamentos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void getAllDepartamentoIsOk() throws Exception {
        Departamento departamento = Departamento
                .builder()
                .id(1l)
                .nome("Departamento1")
                .descricao("Deparatamento1")
                .budget(BigDecimal.valueOf(2000.00))
                .totalSalarios(BigDecimal.ZERO)
                .funcionarios(Arrays.asList(Funcionario.builder().build()))
                .build();

        DepartamentoResponse departamentoResponse = DepartamentoResponse
                .builder()
                .id(1l)
                .nome("Departamento1")
                .descricao("Deparatamento1")
                .budget(BigDecimal.valueOf(2000.00))
                .totalSalarios(BigDecimal.ZERO)
                .funcionarios(Arrays.asList(FuncionarioResponse.builder().build()))
                .build();

        when(service.getAllDepartamento()).thenReturn(Arrays.asList(departamento));
        when(departamentoMapper.toListDto(anyList())).thenReturn(Arrays.asList(departamentoResponse));

        mockMvc.perform(get("/departamentos/todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(departamentoResponse.getId()))
                .andExpect(jsonPath("$[0].nome").value(departamentoResponse.getNome()))
                .andExpect(jsonPath("$[0].budget").value(departamentoResponse.getBudget()))
                .andExpect(jsonPath("$[0].totalSalarios").value(departamentoResponse.getTotalSalarios()))
                .andExpect(jsonPath("$[0].descricao").value(departamentoResponse.getDescricao()))
                .andReturn();
    }
}
