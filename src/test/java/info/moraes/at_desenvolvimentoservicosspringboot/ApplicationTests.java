package info.moraes.at_desenvolvimentoservicosspringboot;

import info.moraes.at_desenvolvimentoservicosspringboot.model.Funcionario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class  ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreateFuncionario() throws Exception {
        // Criar um novo Funcionário simples
        Funcionario newFuncionario = new Funcionario();
        newFuncionario.setNome("Funcionario Teste");
        newFuncionario.setEndereco("Endereco Teste");
        newFuncionario.setTelefone("Telefone Teste");
        newFuncionario.setEmail("emailteste@example.com");
        newFuncionario.setDataNascimento(LocalDate.of(1990, 1, 1));

        mockMvc.perform(post("/api/funcionarios")
                        .with(httpBasic("admin", "admin")) // Adiciona autenticação básica
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newFuncionario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is("Funcionario Teste")));
    }
}
