package com.alexoo.foro_hub.foro_hub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByCursoId(Long cursoId);

    @Query("SELECT t FROM Topico t JOIN FETCH t.curso c JOIN FETCH t.autor a JOIN FETCH a.perfil")
    List<Topico> obtenerTopicos();

    @Query("Select t from Topico t join fetch t.respuestas r where t.id=:id and r.id in (select r.id where r.respuestaPadre is null)")
    Optional<Topico> BuscarPorId(Long id);




}
