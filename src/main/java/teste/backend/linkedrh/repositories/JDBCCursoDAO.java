package teste.backend.linkedrh.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import teste.backend.linkedrh.models.Curso;
import teste.backend.linkedrh.repositories.interfaces.CursoDAO;

@Log4j2
@Repository
public class JDBCCursoDAO implements CursoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Curso> findAll(){
        String sql = "SELECT * FROM curso";
        log.debug("SQL: " + sql);
        return jdbcTemplate.query(sql, new CursoRowMapper());
    }    
   
    @Override
    public Curso findById(int cursoId) {
        String sql = "SELECT * FROM curso WHERE codigo=?";
        log.debug("SQL: " + sql.replace("?", cursoId + ""));

        try{
            return jdbcTemplate.queryForObject(sql, new CursoRowMapper(), cursoId);
        } catch (Exception exception) {
            throw new NotFoundException("Curso não encontrado! Codigo: " + cursoId);
        }
    }

    @Override
    public Curso save(Curso curso) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO curso(nome, descricao, duracao) VALUES (?,?,?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement psmt = connection.prepareStatement(sql, new String[] { "id" });
            psmt.setString(1, curso.getNome());
            psmt.setString(2, curso.getDescricao());
            psmt.setInt(3, curso.getDuracao());
            return psmt;
        }, keyHolder);
        log.debug("SQL: " + sql.replace("?,?,?", curso.getNome() + "," + curso.getDescricao() + "," + curso.getDuracao()));
        
        int generatedId = nonNull(keyHolder.getKey()) ? keyHolder.getKey().intValue() : -1;
        curso.setCodigo(generatedId);

        return curso;
    }

    @Override
    public boolean update(int cursoId, Curso curso) {
        String sql = "UPDATE curso SET nome=?, descricao=?, duracao=? WHERE codigo=?";
        log.debug("SQL: " + sql.replace("nome=?", "nome=" + curso.getNome())
                            .replace("descricao=?", "descricao=?" + curso.getDescricao())
                            .replace("duracao=?", "duracao=" + curso.getDescricao())
                            .replace("codigo=?", "codigo=" + curso.getCodigo()));

        return jdbcTemplate.update(sql, curso.getNome(), curso.getDescricao(), curso.getDuracao(), cursoId) >= 1 ? true : false;
    }

    @Override
    public boolean delete(int cursoId) {      
        String sql = "DELETE FROM curso WHERE codigo=?";
        log.debug("SQL: " + sql.replace("?", cursoId + ""));
        return jdbcTemplate.update(sql, cursoId) >= 1 ? true : false;
    }


    private class CursoRowMapper implements RowMapper<Curso> {
        @Override
        public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {
            Curso curso = new Curso();
            curso.setCodigo(rs.getInt("codigo"));
            curso.setNome(rs.getString("nome"));
            curso.setDescricao(rs.getString("descricao"));
            curso.setDuracao(rs.getInt("duracao"));
            return curso;
        }
    }
    
}
