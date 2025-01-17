package com.alexoo.foro_hub.foro_hub.controller;

import com.alexoo.foro_hub.foro_hub.domain.ValidationException;
import com.alexoo.foro_hub.foro_hub.domain.topico.*;
import com.alexoo.foro_hub.foro_hub.domain.topico.validacion.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topico")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoRetornoDTO> crearTopico(@RequestBody @Valid TopicoIngresoDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        var topico=topicoService.crear(datos);
        TopicoRetornoDTO topicoRetorno = new TopicoRetornoDTO(topico);
        URI uri=uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topicoRetorno.id()).toUri();   // La URI es la URL del recurso creado, otra forma de verlo es que es la URL del recurso que se acaba de crear
        return ResponseEntity.created(uri).body(topicoRetorno);
    }

    @GetMapping
    public ResponseEntity<List<TopicoListarDTO>> listarTopicos() {
        List<Topico> topicos = topicoRepository.obtenerTopicos();
        List<TopicoListarDTO> datos = topicos.stream()
                .map(TopicoListarDTO::new)
                .toList();
        return ResponseEntity.ok(datos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> obtenerTopico(@PathVariable Long id) {
        var topico = topicoRepository.BuscarPorId(id)
                .orElseThrow(() -> new ValidationException("Tópico no encontrado"));
        return ResponseEntity.ok(new TopicoDTO(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<TopicoDTO> actualizarTopico(@RequestBody @Valid TopicoActualizarDTO datos) {
        var topico = topicoService.actualizar(datos);
        return ResponseEntity.ok(new TopicoDTO(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
