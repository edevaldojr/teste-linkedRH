package teste.backend.linkedrh.repositories.interfaces;

import java.util.List;

import teste.backend.linkedrh.models.Turma;
import teste.backend.linkedrh.models.TurmaParticipante;

public interface TurmaDAO {
    
    List<Turma> findAll();
    
    Turma findById(int turmaId);

    List<Turma> findByCursoId(int cursoId);

    Turma save(Turma turma);

    boolean update(int turmaId, Turma turma);

    List<TurmaParticipante> buscarFuncionariosParticipantes(int turmaId);

    boolean addFuncionario(int turmaId, int funcionarioId);

    boolean removeFuncionario(int turmaId, int funcionarioId);

    boolean delete(int turmaId);

}
