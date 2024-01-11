package conta.sistema.dominio.modelo;

// Responsavel por representar um erro de negocio de sistema.
public class NegocioException extends RuntimeException {
    public NegocioException(String mensagem) {
        super(mensagem);
    }
}