package elarning.ms_auth.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import elarning.ms_auth.dto.LoginDTO;
import elarning.ms_auth.entity.Usuario;
import elarning.ms_auth.entity.LogAcesso;
import elarning.ms_auth.repository.UsuarioRepository;
import elarning.ms_auth.repository.LogAcessoRepository;
import elarning.ms_auth.security.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class AuthController {

    private static final Log LOG = LogFactory.getLog(AuthController.class);

    @Autowired
    private UsuarioRepository reposUsuario;

    @Autowired
    private LogAcessoRepository reposLogAcesso;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        LOG.info("Iniciando o login para: " + loginDTO.getEmail());
        
        Usuario usuario = reposUsuario.findByEmail(loginDTO.getEmail())
                .orElse(null);

        if (usuario == null || !passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha())) {
            LOG.warn("Email ou senha inválidos para: " + loginDTO.getEmail());
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }
        
        if (!"ATIVO".equals(usuario.getStatus())) {
             LOG.warn("Usuário inativo tentou logar: " + loginDTO.getEmail());
             return ResponseEntity.status(403).body("Usuário inativo.");
        }

        try {
            String ip = request.getRemoteAddr();
            String userAgent = request.getHeader("User-Agent");
            LogAcesso log = new LogAcesso(usuario.getId(), LocalDateTime.now(), ip, userAgent);
            reposLogAcesso.save(log);

            usuario.setUltimoAcesso(LocalDateTime.now());
            reposUsuario.save(usuario);
            
        } catch (Exception e) {
            LOG.error("Erro ao salvar log de acesso: " + e.getMessage());
        }

        String token = jwtService.gerarToken(usuario); 
        
        LOG.info("Usuario logado com sucesso: " + usuario.getEmail());
        return ResponseEntity.ok(Map.of("token", token));
    }
}