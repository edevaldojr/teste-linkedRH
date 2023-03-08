package teste.backend.linkedrh.repositories.interfaces;

import java.util.List;

import teste.backend.linkedrh.models.Curso;

public interface CursoDAO {
 
    List<Curso> findAll();
    
    Curso findById(int cursoId);

    Curso save(Curso curso);

    boolean update(int cursoId, Curso curso);

    boolean delete(int cursoId);
}
