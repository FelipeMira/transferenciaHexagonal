package teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


import conta.adaptadores.domain.cadastro.response.CadastroResponse;
import conta.adaptadores.interfaces.CadastroCorrentistaFeing;
import conta.adaptadores.repositorio.CadastroRepoImpl;
import conta.sistema.dominio.modelo.Correntista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import teste.config.BuildUm;


@DisplayName("Caso de Uso - Servico de Transferencia")
@SpringBootTest
@ContextConfiguration(classes = BuildUm.class)
public class CadastroRepoImplTest {

    private final CadastroCorrentistaFeing cadastroCorrentistaFeing = Mockito.mock(CadastroCorrentistaFeing.class);

    @InjectMocks
    private CadastroRepoImpl cadastroRepo;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Get Correntista")
    public void testGetCorrentista() {
        // Arrange
        Integer idCorrentista = 1;
        CadastroResponse cadastroResponse = new CadastroResponse();
        cadastroResponse.setIdCorrentista(idCorrentista);
        Correntista expectedCorrentista = new Correntista();
        expectedCorrentista.setIdCorrentista(idCorrentista);

        when(cadastroCorrentistaFeing.getCadastro(idCorrentista)).thenReturn(cadastroResponse);

        // Act
        Correntista actualCorrentista = cadastroRepo.get(idCorrentista);

        // Assert
        assertEquals(expectedCorrentista.getIdCorrentista(), actualCorrentista.getIdCorrentista());
        verify(cadastroCorrentistaFeing, times(1)).getCadastro(idCorrentista);
    }
}