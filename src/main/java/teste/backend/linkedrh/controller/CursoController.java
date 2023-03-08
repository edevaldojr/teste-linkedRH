package teste.backend.linkedrh.controller;

import java.net.URI;
import java.util.List;

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
import teste.backend.linkedrh.models.Curso;
import teste.backend.linkedrh.models.dtos.CursoDTO;
import teste.backend.linkedrh.service.CursoService;

@Log4j2
@RestController
@RequestMapping(path = "/cursos")
public class CursoController {
    
    @Autowired
    private CursoService cursoService;

    @GetMapping("/")
    public ResponseEntity<List<Curso>> getCursos() {
        log.info("Servico de buscar cursos foi chamado");
        return ResponseEntity.ok().body(cursoService.getCursos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCurso(@PathVariable("id") int cursoId) {
        log.info("Servico de buscar um curso especifico foi chamado, curso de código {}", cursoId);
        return ResponseEntity.ok().body(cursoService.getCurso(cursoId));
    }

    @PostMapping("/")
    public ResponseEntity<Curso> criarCurso(@RequestBody CursoDTO cursoDto) {
        log.info("Servico de criar curso foi chamado");
        Curso curso = cursoService.criarCurso(cursoDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(curso.getCodigo()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable("id") int cursoId, @RequestBody CursoDTO cursoDto) {
        log.info("Servico de atualizar um curso especifico foi chamado, curso de código {}", cursoId);
        if(cursoService.atualizarCurso(cursoId, cursoDto)){
            return ResponseEntity.ok().build(); 
        } 
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCurso(@PathVariable("id") int cursoId) {
        log.info("Servico de excluir um curso especifico foi chamado, curso de código {}", cursoId);
        if(cursoService.excluirCurso(cursoId)){
            return ResponseEntity.ok().build(); 
        } 
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    }

}
