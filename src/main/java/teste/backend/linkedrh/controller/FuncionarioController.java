package teste.backend.linkedrh.controller;

import java.net.URI;
import java.util.List;
import static java.util.Objects.nonNull;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import teste.backend.linkedrh.models.Funcionario;
import teste.backend.linkedrh.models.dtos.FuncionarioDTO;
import teste.backend.linkedrh.service.FuncionarioService;

@Log4j2
@RestController
@RequestMapping(path = "/funcionarios")
public class FuncionarioController {
    
    @Autowired
    private FuncionarioService funcionarioService;
    
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/")
    public ResponseEntity<List<Funcionario>> getfuncionarios() {
        log.info("Servico de buscar funcionarios foi chamado");
        if(this.veirificarToken()){
            return ResponseEntity.ok().body(funcionarioService.buscarFuncionarios());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getfuncionario(@PathVariable("id") int funcionarioId) {
        log.info("Servico de buscar um funcionario especifico foi chamado, funcionario de código {}", funcionarioId);
        if(this.veirificarToken()){
            return ResponseEntity.ok().body(funcionarioService.buscarFuncionario(funcionarioId));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); 
    }

    @PostMapping("/")
    public ResponseEntity<Funcionario> criarfuncionario(@Valid @RequestBody FuncionarioDTO funcionarioDto) {
        log.info("Servico de criar funcionario foi chamado");
        if(this.veirificarToken()){
            Funcionario funcionario = funcionarioService.criarFuncionario(funcionarioDto);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(funcionario.getCodigo()).toUri();
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizarfuncionario(@PathVariable("id") int funcionarioId,@Valid @RequestBody FuncionarioDTO funcionarioDto) {
        log.info("Servico de atualizar um funcionario especifico foi chamado, funcionario de código {}", funcionarioId);
        if(this.veirificarToken()){
            if(funcionarioService.atualizarFuncionario(funcionarioId, funcionarioDto)){
                return ResponseEntity.ok().build(); 
            } 
            throw new NotFoundException("Funcionario não encontrado! Codigo: " + funcionarioId);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFuncionario(@PathVariable("id") int funcionarioId) {
        log.info("Servico de excluir um funcionario especifico foi chamado, funcionario de código {}", funcionarioId);
        if(this.veirificarToken()){
            if(funcionarioService.excluirFuncionario(funcionarioId)){
                return ResponseEntity.ok().build(); 
            } 
            throw new NotFoundException("Funcionario não encontrado! Codigo: " + funcionarioId);
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
