package ms_cursos.config;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    // Captura erros de validação de DTOs
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidacao(MethodArgumentNotValidException ex) {
        var erros = ex.getBindingResult().getFieldErrors().stream().map(
                erro -> Map.of("campo", erro.getField(),
                        "mensagem", erro.getDefaultMessage())
        ).toList();
        return ResponseEntity.badRequest().body(Map.of("erros", erros));
    }

    // Captura outros erros (ex: "Categoria não encontrada")
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleErroGeral(RuntimeException ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("erro", ex.getMessage()));
    }
}