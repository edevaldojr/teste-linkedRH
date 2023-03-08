package teste.backend.linkedrh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;
import teste.backend.linkedrh.models.Curso;
import teste.backend.linkedrh.models.dtos.CursoDTO;
import teste.backend.linkedrh.repositories.interfaces.CursoDAO;

@Service
public class CursoService {

    @Autowired
    private CursoDAO cursoDAO;

    @Autowired
    private ModelMapper modelMapper;

    public List<Curso> buscarCursos() {
        List<Curso> curso = cursoDAO.findAll();
        return curso;
    }

    public Curso buscarCurso(int cursoId) {
        Curso curso = cursoDAO.findById(cursoId);
        return curso;
    }

    public Curso criarCurso(CursoDTO cursoDto) {
        Curso curso = this.modelMapper.map(cursoDto, Curso.class);
        curso = cursoDAO.save(curso);
        return curso;
    }

    public boolean atualizarCurso(int cursoId, CursoDTO cursoDto) {
        Curso curso = this.modelMapper.map(cursoDto, Curso.class);
        return cursoDAO.update(cursoId, curso);
    }

    public boolean excluirCurso(int cursoId) {
        return cursoDAO.delete(cursoId);
    }
    
}
