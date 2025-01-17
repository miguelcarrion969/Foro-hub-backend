package com.alexoo.foro_hub.foro_hub.domain.curso;

public record CursoListarDTO(
        Long id,
        String nombre,
        String categoria
) {
    public CursoListarDTO(Curso curso){
        this(curso.getId(), curso.getNombre(), curso.getCategoria().toString());
    }
}
