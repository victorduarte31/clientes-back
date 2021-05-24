package br.victor.clientes.rest.controller;

import br.victor.clientes.model.entity.Usuario;
import br.victor.clientes.rest.dto.TokenDTO;
import br.victor.clientes.rest.service.security.token.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager auth;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid Usuario userForm) {
        UsernamePasswordAuthenticationToken dadosUser = userForm.converter();

        try {
            Authentication authenticate = auth.authenticate(dadosUser);
            String token = tokenService.gerarToken(authenticate);
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
