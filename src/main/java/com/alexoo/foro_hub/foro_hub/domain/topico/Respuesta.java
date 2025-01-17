package com.alexoo.foro_hub.foro_hub.domain.topico;

import com.alexoo.foro_hub.foro_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Respuesta")
@Table(name = "respuesta")
@EqualsAndHashCode(of="id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private Boolean solucion;

    @ManyToOne
    @JoinColumn(name = "respuesta_padre_id")
    private Respuesta respuestaPadre;

    @OneToMany(mappedBy = "respuestaPadre", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestasHijas;

    public Respuesta() {
    }

    public Respuesta(Long id, String mensaje, Topico topico, LocalDate fechaCreacion, Usuario autor, Boolean solucion, Respuesta respuestaPadre, List<Respuesta> respuestasHijas) {
        this.id = id;
        this.mensaje = mensaje;
        this.topico = topico;
        this.fechaCreacion = fechaCreacion;
        this.autor = autor;
        this.solucion = solucion;
        this.respuestaPadre = respuestaPadre;
        this.respuestasHijas = respuestasHijas;
    }

    public Respuesta(RespuestaIngresoDTO datos){
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDate.now();
        this.solucion = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Boolean getSolucion() {
        return solucion;
    }

    public void setSolucion(Boolean solucion) {
        this.solucion = solucion;
    }

    public Respuesta getRespuestaPadre() {
        return respuestaPadre;
    }

    public void setRespuestaPadre(Respuesta respuestaPadre) {
        this.respuestaPadre = respuestaPadre;
    }

    public List<Respuesta> getRespuestasHijas() {
        return respuestasHijas;
    }

    public void setRespuestasHijas(List<Respuesta> respuestasHijas) {
        this.respuestasHijas = respuestasHijas;
    }
}
