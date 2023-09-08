package fotografia.bruna.api.controller;

import fotografia.bruna.api.membro.DadosCadastroMembro;
import fotografia.bruna.api.membro.DadosListagemMembro;
import fotografia.bruna.api.membro.Membro;
import fotografia.bruna.api.membro.MembroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membros")
public class MembrosController {

    @Autowired // Injeção de dependência
    private MembroRepository repository;

    @PostMapping
    @Transactional // Anotação do Spring para indicar que o método é transacional
    // Esse é o padrão de projeto chamado DTO (Data Transfer Object)
    public void cadastrarMembro(@RequestBody @Valid DadosCadastroMembro dados) { // @RequestBody indica que o método vai receber um JSON no corpo da requisição
        repository.save(new Membro(dados)); // Salva o membro no banco de dados
    }

    @GetMapping
    public Page<DadosListagemMembro> listarMembros(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemMembro::new);
    }
}
