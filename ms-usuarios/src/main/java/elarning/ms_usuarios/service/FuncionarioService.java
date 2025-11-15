package elarning.ms_usuarios.service;

import elarning.ms_usuarios.dto.FuncionarioCadastroDTO;
import elarning.ms_usuarios.entity.Funcionario;
import elarning.ms_usuarios.exception.FuncionarioJaExisteException;
import elarning.ms_usuarios.repository.FuncionarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepo;

    @Autowired
    private ModelMapper mapper;

    public void registrar(FuncionarioCadastroDTO dto) {
        
        if (funcionarioRepo.findByCpf(dto.getCpf()).isPresent() || 
            funcionarioRepo.findByEmail(dto.getEmail()).isPresent()) {
            throw new FuncionarioJaExisteException("CPF ou E-mail já cadastrado.");
        }

        Funcionario funcionario = mapper.map(dto, Funcionario.class);

        funcionario.setXpTotal(0);
        funcionario.setNivel("Iniciante");
        funcionario.setStatus("ATIVO");
        funcionario.setDataCadastro(LocalDateTime.now());

        funcionarioRepo.save(funcionario);
    }
}