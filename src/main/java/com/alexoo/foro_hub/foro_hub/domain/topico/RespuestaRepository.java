package com.alexoo.foro_hub.foro_hub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    Optional<Respuesta> findById(Long id);

}
