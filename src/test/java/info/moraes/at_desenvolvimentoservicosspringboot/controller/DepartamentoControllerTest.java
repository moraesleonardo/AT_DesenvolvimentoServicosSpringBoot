package info.moraes.at_desenvolvimentoservicosspringboot.controller;

import info.moraes.at_desenvolvimentoservicosspringboot.model.Departamento;
import info.moraes.at_desenvolvimentoservicosspringboot.service.DepartamentoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartamentoController.class)
public class DepartamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartamentoService departamentoService;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetAllDepartamentos() throws Exception {
        Departamento dept1 = new Departamento("Departamento A");
        Departamento dept2 = new Departamento("Departamento B");
        given(departamentoService.getAllDepartamentos()).willReturn(Arrays.asList(dept1, dept2));

        mockMvc.perform(get("/api/departamentos")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'nome':'Departamento A'},{'nome':'Departamento B'}]"));
    }
}
