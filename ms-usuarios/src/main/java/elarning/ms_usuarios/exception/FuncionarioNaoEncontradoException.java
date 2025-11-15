package elarning.ms_usuarios.exception;

public class FuncionarioNaoEncontradoException extends RuntimeException{
    public FuncionarioNaoEncontradoException(String cpf) {
        super("Funcionário com CPF " + cpf + " não encontrado.");
    }
}