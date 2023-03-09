package teste.backend.linkedrh.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teste.backend.linkedrh.models.Funcionario;
import teste.backend.linkedrh.models.dtos.FuncionarioDTO;
import teste.backend.linkedrh.repositories.interfaces.FuncionarioDAO;

@Service
public class FuncionarioService {
    
    @Autowired
    private FuncionarioDAO funcionarioDAO;

    @Autowired
    private ModelMapper modelMapper;

    public List<Funcionario> buscarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioDAO.findAll();
        return funcionarios;
    }

    public List<Funcionario> buscarFuncionariosFiltrados(String status) {
        List<Funcionario> funcionarios = funcionarioDAO.findAllByStatus(status);
        return funcionarios;
    }

    public Funcionario buscarFuncionario(int funcionarioId) {
        Funcionario funcionario = funcionarioDAO.findById(funcionarioId);
        return funcionario;
    }

    public Funcionario criarFuncionario(FuncionarioDTO funcionarioDto) {
        Funcionario funcionario = this.modelMapper.map(funcionarioDto, Funcionario.class);
        funcionario = funcionarioDAO.save(funcionario);
        return funcionario;
    }

    public boolean atualizarFuncionario(int funcionarioId, FuncionarioDTO funcionarioDto) {
        Funcionario funcionario = this.modelMapper.map(funcionarioDto, Funcionario.class);
        return funcionarioDAO.update(funcionarioId, funcionario);
    }

    public boolean excluirFuncionario(int funcionarioId) {
        return funcionarioDAO.delete(funcionarioId);
    }
    

}
