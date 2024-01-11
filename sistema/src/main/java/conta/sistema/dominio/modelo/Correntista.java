package conta.sistema.dominio.modelo;

import lombok.*;

// Responsavel por representar a entidade correntista e suas regras.
// Nao sera gerenciado pelo IoC e sim pelo repositorio.
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Correntista {
    @Getter@Setter
    private Integer idCorrentista;
    @Getter@Setter
    private String nome;

    public Correntista(Integer idCorrentista){
        this.idCorrentista = idCorrentista;
    }
}
