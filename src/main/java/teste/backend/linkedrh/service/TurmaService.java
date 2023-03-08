package teste.backend.linkedrh.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teste.backend.linkedrh.models.Turma;
import teste.backend.linkedrh.models.dtos.TurmaDTO;
import teste.backend.linkedrh.repositories.interfaces.TurmaDAO;

@Service
public class TurmaService {
    
    @Autowired
    private TurmaDAO turmaDAO;

    @Autowired
    private ModelMapper modelMapper;

    public List<Turma> buscarTurmas() {
        List<Turma> turmas = turmaDAO.findAll();
        return turmas;
    }

    public Turma buscarTurma(int turmaId) {
        Turma turma = turmaDAO.findById(turmaId);
        return turma;
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

    public boolean excluirTurma(int turmaId) {
        return turmaDAO.delete(turmaId);
    }
    

}
