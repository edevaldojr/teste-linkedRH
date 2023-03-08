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
import teste.backend.linkedrh.models.Curso;
import teste.backend.linkedrh.models.dtos.CursoDTO;
import teste.backend.linkedrh.service.CursoService;

@Log4j2
@RestController
@RequestMapping(path = "/cursos")
public class CursoController {
    
    @Autowired
    private CursoService cursoService;

    @Autowired
    private HttpServletRequest request;
    
    @GetMapping("/")
    public ResponseEntity<List<Curso>> buscarCursos() {
        log.info("Servico de buscar cursos foi chamado");
        if(this.veirificarToken()){
            return ResponseEntity.ok().body(cursoService.buscarCursos());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarCurso(@PathVariable("id") int cursoId) {
        log.info("Servico de buscar um curso especifico foi chamado, curso de código {}", cursoId);
        if(this.veirificarToken()){
            return ResponseEntity.ok().body(cursoService.buscarCurso(cursoId));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/")
    public ResponseEntity<Curso> criarCurso(@RequestBody CursoDTO cursoDto) {
        log.info("Servico de criar curso foi chamado");
        if(this.veirificarToken()){ 
            Curso curso = cursoService.criarCurso(cursoDto);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(curso.getCodigo()).toUri();
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable("id") int cursoId, @RequestBody CursoDTO cursoDto) {
        log.info("Servico de atualizar um curso especifico foi chamado, curso de código {}", cursoId);
        if(this.veirificarToken()){ 
            if(cursoService.atualizarCurso(cursoId, cursoDto)){
                return ResponseEntity.ok().build(); 
            } 
            throw new NotFoundException("Curso não encontrado! Codigo: " + cursoId);
        }
       return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCurso(@PathVariable("id") int cursoId) {
        log.info("Servico de excluir um curso especifico foi chamado, curso de código {}", cursoId);
        if(this.veirificarToken()){ 
            if(cursoService.excluirCurso(cursoId)){
                return ResponseEntity.ok().build(); 
            } 
            throw new NotFoundException("Curso não encontrado! Codigo: " + cursoId);
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
