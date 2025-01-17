package com.alexoo.foro_hub.foro_hub.controller;

import com.alexoo.foro_hub.foro_hub.domain.usuario.AutenticacionUsuarioDTO;
import com.alexoo.foro_hub.foro_hub.domain.usuario.Usuario;
import com.alexoo.foro_hub.foro_hub.domain.usuario.UsuarioRepository;
import com.alexoo.foro_hub.foro_hub.infra.security.JWTtokenDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexoo.foro_hub.foro_hub.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid AutenticacionUsuarioDTO autenticacionUsuarioDTO) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(autenticacionUsuarioDTO.email(), autenticacionUsuarioDTO.contrasenha());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario)usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JWTtokenDTO(JWTtoken));
    }

}