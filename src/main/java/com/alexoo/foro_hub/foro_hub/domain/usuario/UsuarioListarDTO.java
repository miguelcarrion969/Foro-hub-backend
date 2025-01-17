package com.alexoo.foro_hub.foro_hub.domain.usuario;

public record UsuarioListarDTO(
        Long id,
        String nombre,
        String email,
        String perfil
) {
    public UsuarioListarDTO(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPerfil().getNombre()
        );
    }
}
