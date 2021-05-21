package br.victor.clientes.rest.controller;

import br.victor.clientes.model.entity.ServicoPrestado;
import br.victor.clientes.rest.dto.ServicoPrestadoDTO;
import br.victor.clientes.rest.service.ServicoPrestadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {

    private final ServicoPrestadoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody ServicoPrestadoDTO servicoPrestadoDTO) {
        return this.service.salvar(servicoPrestadoDTO);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes) {

        return this.service.buscarPorNomeClienteEMes(nome, mes);

    }

}
