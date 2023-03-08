package teste.backend.linkedrh.repositories.interfaces;

import java.util.List;

import teste.backend.linkedrh.models.Funcionario;

public interface FuncionarioDAO {
    
    List<Funcionario> findAll();
    
    Funcionario findById(int funcionarioId);

    Funcionario save(Funcionario funcionario);

    boolean update(int funcionarioId, Funcionario funcionario);

    boolean delete(int funcionarioId);

}
