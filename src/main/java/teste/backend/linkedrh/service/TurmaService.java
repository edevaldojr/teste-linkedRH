package teste.backend.linkedrh.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teste.backend.linkedrh.models.Funcionario;
import teste.backend.linkedrh.models.Turma;
import teste.backend.linkedrh.models.TurmaParticipante;
import teste.backend.linkedrh.models.dtos.TurmaDTO;
import teste.backend.linkedrh.repositories.interfaces.FuncionarioDAO;
import teste.backend.linkedrh.repositories.interfaces.TurmaDAO;

@Service
public class TurmaService {
    
    @Autowired
    private TurmaDAO turmaDAO;

    @Autowired
    private FuncionarioDAO funcionarioDAO;

    @Autowired
    private ModelMapper modelMapper;

    public List<Turma> buscarTurmas() {
        List<Turma> turmas = turmaDAO.findAll();
        turmas.stream().forEach(turma-> {
            List<Funcionario> funcionarios = new ArrayList<>();
            List<TurmaParticipante> participantes = this.buscarFuncionariosParticipantes(turma.getCodigo());
            participantes.stream().forEach(func -> {
                funcionarios.add(funcionarioDAO.findById(func.getFuncionarioId()));
            });
            turma.setFuncionarios(funcionarios);
        });
        return turmas;
    }

    public Turma buscarTurma(int turmaId) {
        List<Funcionario> funcionarios = new ArrayList<>();

        Turma turma = turmaDAO.findById(turmaId);
        List<TurmaParticipante> participantes = this.buscarFuncionariosParticipantes(turma.getCodigo());

        participantes.stream().forEach(func -> {
            funcionarios.add(funcionarioDAO.findById(func.getFuncionarioId()));
        });
        
        turma.setFuncionarios(funcionarios);
        return turma;
    }

    public List<Turma> buscarTurmasPorCurso(int cursoId) {
        List<Turma> turmas = turmaDAO.findByCursoId(cursoId);
        return turmas;
    }

    public Turma criarTurma(TurmaDTO turmaDto) {
        Turma turma = this.modelMapper.map(turmaDto, Turma.class);
        turma = turmaDAO.save(turma);
        return turma;
    }

    public boolean atualizarTurma(int turmaId, TurmaDTO turmaDto) {
        Turma turma = this.modelMapper.map(turmaDto, Turma.class);
        return turmaDAO.update(turmaId, turma);
    }

    private List<TurmaParticipante> buscarFuncionariosParticipantes(int turmaId) { 
        return turmaDAO.buscarFuncionariosParticipantes(turmaId);
    }
    
    public boolean adicionarFuncionario(int turmaId, int funcionarioId) {
        Funcionario funcionario = funcionarioDAO.findById(funcionarioId);
        return turmaDAO.addFuncionario(turmaId, funcionario.getCodigo());
    }

    public boolean removerFuncionario(int turmaId, int funcionarioId) {
        Funcionario funcionario = funcionarioDAO.findById(funcionarioId);
        return turmaDAO.removeFuncionario(turmaId, funcionario.getCodigo());
    }

    public boolean excluirTurma(int turmaId) {
        return turmaDAO.delete(turmaId);
    }


}
