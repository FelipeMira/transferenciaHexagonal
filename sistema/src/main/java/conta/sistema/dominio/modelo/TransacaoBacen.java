package conta.sistema.dominio.modelo;

import lombok.*;

import java.math.BigDecimal;

// Responsavel por representar a entidade conta e suas regras.
// Nao sera gerenciado pelo IoC e sim pelo repositorio.
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransacaoBacen {
    @Getter@Setter
    private Integer idDebito;
    @Getter@Setter
    private Integer idCredito;
    @Getter@Setter
    private BigDecimal valor;

}
