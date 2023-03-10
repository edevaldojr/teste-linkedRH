package teste.backend.linkedrh.repositories;

import java.sql.Date;
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
import teste.backend.linkedrh.models.Funcionario;
import teste.backend.linkedrh.repositories.interfaces.FuncionarioDAO;

@Log4j2
@Repository
public class JDBCFuncionarioDAO implements FuncionarioDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Funcionario> findAll(){
        String sql = "SELECT * FROM funcionario";
        log.debug("SQL: " + sql);
        return jdbcTemplate.query(sql, new FuncionarioRowMapper());
    }

    @Override
    public List<Funcionario> findAllByStatus(String status) {
        String sql = "SELECT * FROM funcionario WHERE status=?";
        log.debug("SQL: " + sql.replace("?", status));
        return jdbcTemplate.query(sql.replace("?", status), new FuncionarioRowMapper());
    }

   
    @Override
    public Funcionario findById(int funcionarioId) {
        String sql = "SELECT * FROM funcionario WHERE codigo=?";
        log.debug("SQL: " + sql.replace("?", funcionarioId + ""));
        try{
            return jdbcTemplate.queryForObject(sql, new FuncionarioRowMapper(), funcionarioId);
        } catch (Exception exception) {
            throw new NotFoundException("Funcionario não encontrado! Codigo: " + funcionarioId);
        }
        
    }

    @Override
    public Funcionario save(Funcionario funcionario) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO funcionario(nome, cpf, nascimento, cargo, admissao, status) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement psmt = connection.prepareStatement(sql, new String[] { "id" });
            psmt.setString(1, funcionario.getNome());
            psmt.setString(2, funcionario.getCpf());
            psmt.setDate(3, Date.valueOf(funcionario.getNascimento()));
            psmt.setString(4, funcionario.getCargo());
            psmt.setDate(5, Date.valueOf(funcionario.getAdmissao()));
            psmt.setBoolean(6, funcionario.isStatus());

            return psmt;
        }, keyHolder);
        log.debug("SQL: " + sql.replace("?,?,?,?,?,?", funcionario.getNome() + "," + funcionario.getNascimento() + "," + funcionario.getCargo() + "," + funcionario.getAdmissao()+ "," + funcionario.isStatus()));
        
        int generatedId = nonNull(keyHolder.getKey()) ? keyHolder.getKey().intValue() : -1;
        funcionario.setCodigo(generatedId);

        return funcionario;
    }

    @Override
    public boolean update(int funcionarioId, Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nome=?, cpf=?, nascimento=?, cargo=?, admissao=?, status=? WHERE codigo=?";
        log.debug("SQL: " + sql.replace("nome=?", "nome=" + funcionario.getNome())
                            .replace("cpf=?", "cpf=?" + funcionario.getCpf())
                            .replace("nascimento=?", "nascimento=" + funcionario.getNascimento())
                            .replace("cargo=?", "cargo=" + funcionario.getCargo())
                            .replace("admissao=?", "admissao=" + funcionario.getAdmissao())
                            .replace("codigo=?", "codigo=" + funcionario.getCodigo())
                            .replace("status=?", funcionario.isStatus() ? "status=1" : "status=0"));

        return jdbcTemplate.update(sql, funcionario.getNome(), funcionario.getCpf(), funcionario.getNascimento(), funcionario.getCargo(), funcionario.getAdmissao(), funcionario.isStatus(), funcionarioId) >= 1 ? true : false;
    }

    @Override
    public boolean delete(int id) {      
        String sql = "UPDATE funcionario SET status=0 WHERE codigo=?";
        log.debug("SQL: " + sql.replace("?", id + ""));
        return jdbcTemplate.update(sql, id) >= 1 ? true : false;
    }


    private class FuncionarioRowMapper implements RowMapper<Funcionario> {
        @Override
        public Funcionario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Funcionario funcionario = new Funcionario();
            funcionario.setCodigo(rs.getInt("codigo"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setNascimento(rs.getDate("nascimento").toLocalDate());
            funcionario.setCargo(rs.getString("cargo"));
            funcionario.setAdmissao(rs.getDate("admissao").toLocalDate());
            funcionario.setStatus(rs.getBoolean("status"));

            return funcionario;
        }
    }

}
