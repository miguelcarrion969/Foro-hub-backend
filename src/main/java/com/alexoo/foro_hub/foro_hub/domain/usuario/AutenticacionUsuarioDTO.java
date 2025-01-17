package com.alexoo.foro_hub.foro_hub.domain.usuario;

public record AutenticacionUsuarioDTO(
        Long id,
        String email,
        String contrasenha
) {
    public AutenticacionUsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getEmail(), usuario.getContrasenha());
    }
}
