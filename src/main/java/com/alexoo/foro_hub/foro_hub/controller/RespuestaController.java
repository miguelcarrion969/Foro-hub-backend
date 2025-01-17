package com.alexoo.foro_hub.foro_hub.controller;

import com.alexoo.foro_hub.foro_hub.domain.ValidationException;
import com.alexoo.foro_hub.foro_hub.domain.topico.*;
import com.alexoo.foro_hub.foro_hub.domain.topico.validacion.ValidacionEliminarRespuesta;
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
@RequestMapping("/respuesta")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private RespuestaService respuestaService;

    @Autowired
    private List<ValidacionEliminarRespuesta> validaciones;

    @PostMapping
    @Transactional
    public ResponseEntity<RespuestaDTO> crearRespuesta(@RequestBody @Valid RespuestaIngresoDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        var respuesta=respuestaService.responder(datos);
        RespuestaDTO respuestaDTO = new RespuestaDTO(respuesta);
        URI uri=uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(respuestaDTO.id()).toUri();   // La URI es la URL del recurso creado, otra forma de verlo es que es la URL del recurso que se acaba de crear
        return ResponseEntity.created(uri).body(respuestaDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id) {
        var respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Respuesta no encontrada"));

        // Validaciones
        validaciones.forEach(validacion -> validacion.validar(respuesta));

        respuestaRepository.delete(respuesta);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaDTO> actualizarRespuesta(@RequestBody @Valid RespuestaActualizarDTO datos) {
        var respuesta=respuestaService.actualizar(datos);
        return ResponseEntity.ok(respuesta);
    }


}
