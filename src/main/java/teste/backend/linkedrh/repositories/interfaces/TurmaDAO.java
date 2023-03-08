package teste.backend.linkedrh.repositories.interfaces;

import java.util.List;

import teste.backend.linkedrh.models.Turma;

public interface TurmaDAO {
    
    List<Turma> findAll();
    
    Turma findById(int turmaId);

    Turma save(Turma turma);

    boolean update(int turmaId, Turma turma);

    boolean delete(int turmaId);

}
