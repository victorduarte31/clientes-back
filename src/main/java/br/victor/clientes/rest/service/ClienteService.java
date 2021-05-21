package br.victor.clientes.rest.service;

import br.victor.clientes.model.entity.Cliente;
import br.victor.clientes.rest.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
        cliente.setDataCadastro(LocalDate.now());
        return this.clienteRepository.save(cliente);
    }

    public Cliente findById(Long id) {
        return this.clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deletarClientePorId(Long id) {
        var cliente = findById(id);
        if (cliente != null) {
            this.clienteRepository.delete(cliente);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public void atualizar(Long id, Cliente clienteAtualizado) {
        var cliente = findById(id);
        if (cliente != null) {
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setCpf(clienteAtualizado.getCpf());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<Cliente> buscarTodos() {
        return this.clienteRepository.findAll();
    }
}
