package com.alexoo.foro_hub.foro_hub.domain.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "Perfil")
@Table(name = "perfil")
@EqualsAndHashCode(of="id")
public class Perfil {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private Boolean activo;

    public Perfil() {
    }

    public Perfil(Long id, String nombre, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
