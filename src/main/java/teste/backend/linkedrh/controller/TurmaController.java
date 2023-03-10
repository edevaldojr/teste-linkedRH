package teste.backend.linkedrh.controller;

import java.net.URI;
import java.util.List;
import static java.util.Objects.nonNull;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.log4j.Log4j2;
import teste.backend.linkedrh.exceptions.NotFoundException;
import teste.backend.linkedrh.models.Turma;
import teste.backend.linkedrh.models.dtos.TurmaDTO;
import teste.backend.linkedrh.service.TurmaService;

@Log4j2
@RestController
@RequestMapping(path = "/turmas")
public class TurmaController {
    
    @Autowired
    private TurmaService turmaService;

    @Autowired
    private HttpServletRequest request;
    
    @GetMapping("/")
    public ResponseEntity<List<Turma>> buscarTurmas() {
        log.info("Servico de buscar turmas foi chamado");
        if(this.veirificarToken()){
            return ResponseEntity.ok().body(turmaService.buscarTurmas());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarTurma(@PathVariable("id") int turmaId) {
        log.info("Servico de buscar um turma especifico foi chamado, turma de código {}", turmaId);
        if(this.veirificarToken()){
            return ResponseEntity.ok().body(turmaService.buscarTurma(turmaId));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Turma>> buscarTurmaPorCurso(@PathVariable("cursoId") int cursoId) {
        log.info("Servico de buscar uma turma especifica pela código do curso foi chamado, curso de código {}", cursoId);
        if(this.veirificarToken()){
            return ResponseEntity.ok().body(turmaService.buscarTurmasPorCurso(cursoId));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/")
    public ResponseEntity<Turma> criarTurma(@RequestBody TurmaDTO turmaDto) {
        log.info("Servico de criar turma foi chamado");
        if(this.veirificarToken()){ 
            Turma turma = turmaService.criarTurma(turmaDto);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(turma.getCodigo()).toUri();
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizarTurma(@PathVariable("id") int turmaId, @RequestBody TurmaDTO turmaDto) {
        log.info("Servico de atualizar um turma especifico foi chamado, turma de código {}", turmaId);
        if(this.veirificarToken()){ 
            if(turmaService.atualizarTurma(turmaId, turmaDto)){
                return ResponseEntity.ok().build(); 
            } 
            throw new NotFoundException("Turma não encontrada! Codigo: " + turmaId);
        }
       return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PutMapping("/{turmaId}/addFuncionario/{funcionarioId}")
    public ResponseEntity<Turma> adicionarFuncionario(@PathVariable("turmaId") int turmaId, @PathVariable("funcionarioId") int funcionarioId) {
        log.info("Servico de adicionar um funcionario a uma turma especifica foi chamado, turma de código {} e funcionario de código {}", turmaId, funcionarioId);
        if(this.veirificarToken()){ 
            if(turmaService.adicionarFuncionario(turmaId, funcionarioId)){
                return ResponseEntity.ok().build(); 
            } 
            throw new NotFoundException("Turma não encontrada! Codigo: " + turmaId);
        }
       return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @DeleteMapping("/{turmaId}/removeFuncionario/{funcionarioId}")
    public ResponseEntity<Turma> removerFuncionario(@PathVariable("turmaId") int turmaId, @PathVariable("funcionarioId") int funcionarioId) {
        log.info("Servico de remover um funcionario a uma turma especifica foi chamado, turma de código {} e funcionario de código {}", turmaId, funcionarioId);
        if(this.veirificarToken()){ 
            if(turmaService.removerFuncionario(turmaId, funcionarioId)){
                return ResponseEntity.ok().build(); 
            } 
            throw new NotFoundException("Turma não encontrada ou funcionário não é participante desta turma! Codigo: " + turmaId);
        }
       return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTurma(@PathVariable("id") int turmaId) {
        log.info("Servico de excluir um turma especifico foi chamado, turma de código {}", turmaId);
        if(this.veirificarToken()){ 
            if(turmaService.excluirTurma(turmaId)){
                return ResponseEntity.ok().build(); 
            } 
            throw new NotFoundException("Turma não encontrada! Codigo: " + turmaId);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

     private boolean veirificarToken() {
        log.info("Verificando token da requisição http");
        String authHeader = request.getHeader("Authorization");
        if (nonNull(authHeader) && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // remove prefixo "Bearer "
            // Faça algo com o token...
            return true;
        }
        return false;
    }

}
