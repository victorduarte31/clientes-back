package br.victor.clientes.rest.service;

import br.victor.clientes.model.entity.ServicoPrestado;
import br.victor.clientes.rest.dto.ServicoPrestadoDTO;
import br.victor.clientes.rest.repository.ClienteRepository;
import br.victor.clientes.rest.repository.ServicoPrestadoRepository;
import br.victor.clientes.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoPrestadoService {

    private final ServicoPrestadoRepository servicoPrestadoRepository;
    private final ClienteRepository clienteRepository;

    private final BigDecimalConverter bigDecimalConverter;

    public ServicoPrestado salvar(ServicoPrestadoDTO servicoPrestadoDTO) {
        var cliente = clienteRepository.findById(Long.valueOf(servicoPrestadoDTO.getIdCliente())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return servicoPrestadoRepository.save(ServicoPrestado.builder()
                .descricao(servicoPrestadoDTO.getDescricao())
                .data(LocalDate.parse(servicoPrestadoDTO.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cliente(cliente)
                .valor(bigDecimalConverter.convert(servicoPrestadoDTO.getPreco())).build());
    }

    public List<ServicoPrestado> buscarPorNomeClienteEMes(String nome, Integer mes) {

        return this.servicoPrestadoRepository.findByNomeClienteAndMes("%" + nome + "%", mes);

    }
}
