package teste.backend.linkedrh.repositories;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.nonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j2;
import teste.backend.linkedrh.exceptions.NotFoundException;
import teste.backend.linkedrh.models.Turma;
import teste.backend.linkedrh.models.TurmaParticipante;
import teste.backend.linkedrh.repositories.interfaces.TurmaDAO;

@Log4j2
@Repository
public class JDBCTurmaDAO implements TurmaDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Turma> findAll(){
        String sql = "SELECT * FROM turma";
        log.debug("SQL: " + sql);
        List<Turma> turmas = jdbcTemplate.query(sql, new TurmaRowMapper());
        return turmas;
    }    
   
    @Override
    public Turma findById(int turmaId) {
        String sql = "SELECT * FROM turma WHERE codigo=?";
        log.debug("SQL: " + sql.replace("?", turmaId + ""));
       
        try {
            return jdbcTemplate.queryForObject(sql, new TurmaRowMapper(), turmaId);
        } catch (Exception exception) {
            throw new NotFoundException("Turma não encontrada! Codigo: " + turmaId);
        }

    }

    @Override
    public List<Turma> findByCursoId(int cursoId) {
        String sql = "SELECT * FROM turma WHERE curso_id=?";
        log.debug("SQL: " + sql.replace("?", cursoId + ""));
       
        try {
            return jdbcTemplate.query(sql, new TurmaRowMapper(), cursoId);
        } catch (Exception exception) {
            throw new NotFoundException("Curso não encontrada! Codigo: " + cursoId);
        }
    }

    @Override
    public Turma save(Turma turma) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO turma(inicio, fim, local, curso_id) VALUES (?,?,?,?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement psmt = connection.prepareStatement(sql, new String[] { "id" });
            psmt.setDate(1, Date.valueOf(turma.getInicio()));
            psmt.setDate(2, Date.valueOf(turma.getFim()));
            psmt.setString(3, turma.getLocal());
            psmt.setInt(4, turma.getCursoId());

            return psmt;
        }, keyHolder);
        log.debug("SQL: " + sql.replace("?,?,?,?", turma.getInicio() + "," + turma.getFim() + "," + turma.getLocal()));
        
        int generatedId = nonNull(keyHolder.getKey()) ? keyHolder.getKey().intValue() : -1;
        turma.setCodigo(generatedId);

        return turma;
    }

    @Override
    public boolean update(int turmaId, Turma turma) {
        String sql = "UPDATE turma SET inicio=?, fim=?, local=? WHERE codigo=?";
        log.debug("SQL: " + sql.replace("inicio=?", "inicio=" + turma.getInicio())
                            .replace("fim=?", "fim=" + turma.getFim())
                            .replace("local=?", "local=" + turma.getLocal())
                            .replace("codigo=?", "codigo=" + turmaId));

        return jdbcTemplate.update(sql, turma.getInicio(), turma.getFim(), turma.getLocal(), turma.getCursoId(), turmaId) >= 1 ? true : false;
    }

    @Override
    public List<TurmaParticipante> buscarFuncionariosParticipantes(int turmaId) {
        String sql = "SELECT * FROM turmaparticipante WHERE turma_id=?";
        log.debug("SQL: " + sql.replace("?", turmaId + ""));
        try {
           return jdbcTemplate.query(sql, new TurmaParticipantesRowMapper(), turmaId);
        } catch (Exception exception) {
            throw new NotFoundException("cU! Codigo: " + turmaId);
        }
    }

    @Override
    public boolean addFuncionario(int turmaId, int funcionarioId) {
        String sql = "INSERT INTO turmaparticipante(turma_id, funcionario_id) VALUES (?,?)";
        log.debug("SQL: " + sql.replace("?,?", turmaId + "," + funcionarioId));
        return jdbcTemplate.update(sql, turmaId, funcionarioId) >= 1 ? true : false;
    }
    
    @Override
    public boolean removeFuncionario(int turmaId, int funcionarioId) {
        String sql = "DELETE FROM turmaparticipante WHERE turma_id=? AND funcionario_id=?";
        log.debug("SQL: " + sql.replace("?,?", turmaId + "," + funcionarioId));
        return jdbcTemplate.update(sql, turmaId, funcionarioId) >= 1 ? true : false;
    }

    @Override
    public boolean delete(int id) {      
        String sql = "DELETE FROM turma WHERE codigo=?";
        log.debug("SQL: " + sql.replace("?", id + ""));
        return jdbcTemplate.update(sql, id) >= 1 ? true : false;
    }


    private class TurmaRowMapper implements RowMapper<Turma> {
        @Override
        public Turma mapRow(ResultSet rs, int rowNum) throws SQLException {
            Turma turma = new Turma();
            turma.setCodigo(rs.getInt("codigo"));
            turma.setInicio(rs.getDate("inicio").toLocalDate());
            turma.setFim(rs.getDate("fim").toLocalDate());
            turma.setLocal(rs.getString("local"));
            turma.setCursoId(rs.getInt("curso_id"));
            turma.setFuncionarios(new ArrayList<>());
            return turma;
        }
        
    }

    private class TurmaParticipantesRowMapper implements RowMapper<TurmaParticipante> {
        @Override
        public TurmaParticipante mapRow(ResultSet rs, int rowNum) throws SQLException {
            TurmaParticipante participante = new TurmaParticipante();
            participante.setCodigo(rs.getInt("codigo"));
            participante.setFuncionarioId(rs.getInt("funcionario_id"));
            participante.setTurmaId(rs.getInt("turma_id"));

            return participante;
        }
        
    }

}
