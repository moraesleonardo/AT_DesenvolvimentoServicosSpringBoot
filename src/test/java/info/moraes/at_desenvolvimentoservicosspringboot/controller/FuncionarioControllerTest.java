package info.moraes.at_desenvolvimentoservicosspringboot.controller;

import info.moraes.at_desenvolvimentoservicosspringboot.model.Funcionario;
import info.moraes.at_desenvolvimentoservicosspringboot.service.FuncionarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(FuncionarioController.class)
public class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuncionarioService funcionarioService;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetAllFuncionarios() throws Exception {
        Funcionario func1 = new Funcionario("Funcionario 1", "Endereco 1", "Telefone 1", "email1@example.com", LocalDate.of(1990, 1, 1), null);
        Funcionario func2 = new Funcionario("Funcionario 2", "Endereco 2", "Telefone 2", "email2@example.com", LocalDate.of(1991, 2, 2), null);
        given(funcionarioService.getAllFuncionarios()).willReturn(Arrays.asList(func1, func2));

        mockMvc.perform(get("/api/funcionarios")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'nome':'Funcionario 1'},{'nome':'Funcionario 2'}]"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testCreateFuncionario() throws Exception {
        Funcionario newFuncionario = new Funcionario("Funcionario Teste", "Endereco Teste", "Telefone Teste", "emailteste@example.com", LocalDate.of(1990, 1, 1), null);
        Funcionario savedFuncionario = new Funcionario("Funcionario Teste", "Endereco Teste", "Telefone Teste", "emailteste@example.com", LocalDate.of(1990, 1, 1), null);
        savedFuncionario.setId(1L); // Definir um ID para o objeto salvo

        when(funcionarioService.createFuncionario(any(Funcionario.class))).thenReturn(savedFuncionario);

        mockMvc.perform(post("/api/funcionarios")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())  // Adicionar token CSRF
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"Funcionario Teste\", \"endereco\": \"Endereco Teste\", \"telefone\": \"Telefone Teste\", \"email\": \"emailteste@example.com\", \"dataNascimento\": \"1990-01-01\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is("Funcionario Teste")))
                .andExpect(jsonPath("$.endereco", is("Endereco Teste")))
                .andExpect(jsonPath("$.telefone", is("Telefone Teste")))
                .andExpect(jsonPath("$.email", is("emailteste@example.com")))
                .andExpect(jsonPath("$.dataNascimento", is("1990-01-01")));
    }
}
