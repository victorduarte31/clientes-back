package br.victor.clientes.rest.controller;

import br.victor.clientes.model.entity.Cliente;
import br.victor.clientes.rest.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@ResponseBody
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> buscarTodosClientes() {
        return clienteService.buscarTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody @Valid Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente acharPorId(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        clienteService.deletarClientePorId(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
        clienteService.atualizar(id, cliente);
    }

}
