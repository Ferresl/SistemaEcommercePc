package com.DuocucEcommerce.Notificacion.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DuocucEcommerce.Notificacion.Model.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {

    List<Notificacion> findByUsuarioId(Integer usuarioId);
}
